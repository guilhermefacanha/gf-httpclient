package http.client;

import java.util.HashMap;

import http.client.enumeration.RequestMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GFCall {
	private RequestMethod method;
	private String url;
	private MediaType type;
	private Object payload;
	HashMap<String, String> params;
	HashMap<String, String> headers;
}
