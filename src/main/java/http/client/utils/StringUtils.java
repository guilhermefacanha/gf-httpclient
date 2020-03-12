package http.client.utils;

import com.google.gson.Gson;

public class StringUtils {
	public static String tryParse(byte[] bytes) {
		try {
			return new String(bytes);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String getJson(byte[] bytes) {
		try {
			return new Gson().fromJson(tryParse(bytes), Object.class).toString();
		} catch (Exception e) {
			return "";
		}
	}
}
