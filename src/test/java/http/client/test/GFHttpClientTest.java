package http.client.test;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;

import http.client.GFCall;
import http.client.GFHttpClient;
import http.client.GFResponse;
import http.client.enumeration.RequestMethod;

public class GFHttpClientTest {
	
	@Test
	public void testGet() throws IOException {
		HashMap<String, String> params = new HashMap<>();
		params.put("name", "john");
		params.put("email", "john@email.com");
		GFCall call = GFCall.builder()
				.method(RequestMethod.GET)
				.url("https://my.api.mockaroo.com/movies.json?key=5f718f00")
				.params(params)
				.build();
		
		GFResponse resp = GFHttpClient.call(call);
		Assert.assertEquals(resp.getCode(), 200);
	}
	
	@Test
	public void testPost() throws IOException {
		JsonObject json = new JsonObject();
		json.addProperty("name", "john");
		json.addProperty("email", "john@email.com");
		
		HashMap<String, String> params = new HashMap<>();
		params.put("name", "john");
		params.put("email", "john@email.com");
		GFCall call = GFCall.builder()
								.method(RequestMethod.POST)
								.url("https://app.gfsolucoesti.com.br/servlet/service")
								.type(GFHttpClient.JSON)
								.payload(json)
								.params(params)
								.build();
		
		GFResponse resp = GFHttpClient.call(call);
		Assert.assertEquals(resp.getCode(), 200);
	}
	
}
