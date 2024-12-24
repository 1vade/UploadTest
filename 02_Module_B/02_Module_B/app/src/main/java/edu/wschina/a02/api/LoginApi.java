package edu.wschina.a02.api;

import android.content.Context;

import edu.wschina.a02.util.HttpUtil;

public class LoginApi {
	public static void login(Context context, String username, String password, HttpUtil.Callback<ResponseMo> callback) {
		HttpUtil.postObject(context, "/auth/login", new RequestMo(username, password), false, ResponseMo.class, callback);
	}
	
	public static class RequestMo {
		
		public final String username;
		public final String password;
		
		public RequestMo(String username, String password) {
			this.username = username;
			this.password = password;
		}
	}
	
	public static class ResponseMo {
		public int code;
		public String msg;
		public String data;
	}
}
