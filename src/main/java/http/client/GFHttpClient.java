package http.client;

import java.io.IOException;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import http.client.enumeration.RequestMethod;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Helper class to connect to rest services <br/>
 * See {@link GFCall} <br/>
 * <br/>
 * <b>usage:</b><br/>
 * GFCall call = GFCall.builder()<br/>
 * .method(RequestMethod.GET)<br/>
 * .url("http://host/service")<br/>
 * .build();<br/>
 * GFHttpClient.call(call);
 * 
 */
public class GFHttpClient {
	private GFHttpClient() {
		throw new InstantiationError("Singleton Class");
	}

	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

	public static GFResponse call(GFCall call) throws IOException {
		validateCall(call);
		if (RequestMethod.GET.equals(call.getMethod())) {
			return get(call);
		} else if (RequestMethod.POST.equals(call.getMethod())) {
			return post(call);
		} else if (RequestMethod.DELETE.equals(call.getMethod())) {
			throw new RuntimeException("Method " + call.getMethod() + " not implemented!");
		} else if (RequestMethod.HEAD.equals(call.getMethod())) {
			throw new RuntimeException("Method " + call.getMethod() + " not implemented!");
		} else if (RequestMethod.OPTIONS.equals(call.getMethod())) {
			throw new RuntimeException("Method " + call.getMethod() + " not implemented!");
		} else if (RequestMethod.PATCH.equals(call.getMethod())) {
			throw new RuntimeException("Method " + call.getMethod() + " not implemented!");
		} else if (RequestMethod.PUT.equals(call.getMethod())) {
			throw new RuntimeException("Method " + call.getMethod() + " not implemented!");
		} else if (RequestMethod.TRACE.equals(call.getMethod())) {
			throw new RuntimeException("Method " + call.getMethod() + " not implemented!");
		}
		return null;
	}

	private static GFResponse get(GFCall call) throws IOException {
		OkHttpClient client = getHttpClient();

		HttpUrl.Builder httpBuilder = getUrlWithParams(call);
		
		Request request = new Request.Builder()
									.url(httpBuilder.build())
									.build();

		addHeaders(request, call);

		try (Response response = client.newCall(request).execute()) {
			return GFResponse.builder()
							.code(response.code())
							.headers(response.headers())
							.contentType(response.body().contentType())
							.content(response.body().string())
							.build();
		}
	}
	
	private static GFResponse post(GFCall call) throws IOException {
		OkHttpClient client = getHttpClient();

		HttpUrl.Builder httpBuilder = getUrlWithParams(call);

		RequestBody body = RequestBody
								.create(call.getPayload().toString(), call.getType());
		
		Request request = new Request.Builder()
								.url(httpBuilder.build())
								.post(body)
								.build();
		
		addHeaders(request, call);
		
		try (Response response = client.newCall(request).execute()) {
			return GFResponse.builder()
					.code(response.code())
					.headers(response.headers())
					.content(response.body().string())
					.build();
		}
	}

	private static HttpUrl.Builder getUrlWithParams(GFCall call) {
		HttpUrl.Builder httpBuilder = HttpUrl.parse(call.getUrl()).newBuilder();
		if(call.getParams()!=null) {
			for (Entry<String, String> entry : call.getParams().entrySet()) {
				httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
			}
		}
		return httpBuilder;
	}

	private static void addHeaders(Request request, GFCall call) {
		if(call.getHeaders()!=null) {
			for (Entry<String, String> entry : call.getHeaders().entrySet()) {
				request.newBuilder().addHeader(entry.getKey(), entry.getValue());
			}
		}
	}

	private static void validateCall(GFCall call) {
		if (call.getMethod() == null)
			throw new IllegalArgumentException("Attribute method is required!");
		else if (call.getUrl() == null || call.getUrl().isEmpty())
			throw new IllegalArgumentException("Attribute url is required!");
		else if(RequestMethod.POST.equals(call.getMethod())) {
			if(call.getType()==null)
				throw new IllegalArgumentException("Attribute type is required for "+call.getMethod()+"!");
			else if(call.getPayload()==null)
				throw new IllegalArgumentException("Attribute payload is required for "+call.getMethod()+"!");
		}
	}

	private static OkHttpClient getHttpClient() {
		return getUnsafeOkHttpClient();
	}

	private static OkHttpClient getUnsafeOkHttpClient() {
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}
			} };

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			return builder.build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
