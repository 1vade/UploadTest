package edu.wschina.a02.api;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.wschina.a02.util.HttpUtil;

public class RegisterApi {
	public static void register(Context context, String username, String password, String email, HttpUtil.Callback<ResponseMo> callback) {
		Map<String, Object> formData = new HashMap<>();
		formData.put("username", username);
		formData.put("password", password);
		formData.put("email", email);
		HttpUtil.postFormData(context, "/auth/register", formData, false, ResponseMo.class, callback);
	}
	
	public static class ResponseMo {
		public int code;
		public String msg;
		public String data;
	}
	
}
