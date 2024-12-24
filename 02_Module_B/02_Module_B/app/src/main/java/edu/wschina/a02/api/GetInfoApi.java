package edu.wschina.a02.api;

import android.content.Context;

import edu.wschina.a02.util.HttpUtil;

public class GetInfoApi {
	public static void get(Context context, HttpUtil.Callback<ResponseMo> callback) {
		HttpUtil.get(context, "/user/info", null, true, ResponseMo.class, callback);
	}
	
	public static class ResponseMo {
		public int code;
		public String msg;
		public Data data;
		
	}
	
	public static class Data {
		public String username;
		public String email;
	}
	
	
}
