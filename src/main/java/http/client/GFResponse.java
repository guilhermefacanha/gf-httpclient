package http.client;

import lombok.Builder;
import lombok.Data;
import okhttp3.Headers;
import okhttp3.MediaType;

@Builder
@Data
public class GFResponse {
	private int code;
	private Headers headers;
	private String content;
	private byte[] contentByte;
	private MediaType contentType;
	
}
