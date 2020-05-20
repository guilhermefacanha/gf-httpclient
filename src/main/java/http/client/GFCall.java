package http.client;

import java.util.Map;

import http.client.enumeration.RequestMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;

/**
 * Client Class to perform Http based Calls using OkHttp 4. <br/>
 * <br/>
 * See {@link GFHttpClient} <br/>
 * <br/>
 * <b>Example usage:</b> <br/>
 * &ensp;HashMap<String, String> params = new HashMap<>(); <br/>
 * &ensp; params.put("name", "john"); <br/>
 * &ensp; params.put("email", "john@email.com"); <br/>
 * &ensp; GFCall call = GFCall.builder() <br/>
 * &ensp; .method(RequestMethod.GET) <br/>
 * &ensp; .url("https://my.api.mockaroo.com/movies.json?key=5f718f00") <br/>
 * &ensp; .params(params) <br/>
 * &ensp; .build(); <br/>
 * &ensp; <br/>
 * &ensp; GFResponse resp = GFHttpClient.call(call);
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GFCall {
    private RequestMethod method;
    private String url;
    private MediaType type;
    private Object payload;
    Map<String, String> params;
    Map<String, String> headers;

}
