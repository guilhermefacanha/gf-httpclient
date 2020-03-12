package http.client;

import lombok.Builder;
import lombok.Data;
import okhttp3.Headers;
import okhttp3.MediaType;

/**
 * Response Class from a Http Call.
 * <br/>
 * Will store the response code, headers, content, contentType;
 * */
@Builder
@Data
public class GFResponse {
	private int code;
	private Headers headers;
	private String content;
	private byte[] contentByte;
	private MediaType contentType;
	
}
