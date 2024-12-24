package edu.wschina.a02.api;

import android.content.Context;

import java.util.List;

import edu.wschina.a02.util.HttpUtil;

public class GetMyPictureApi {
	public static void get(Context context, HttpUtil.Callback<ResponseMo> callback) {
		HttpUtil.get(context, "/user/pictures?pageNumber=1&pageSize=10", null, true, ResponseMo.class, callback);
	}
	
	public static class ResponseMo {
		public int code;
		public String msg;
		public Data data;
	}
	
	public static class Data {
		public List<MyPicMo> list;
		public int total;
	}
	
	public static class MyPicMo {
		public int id;
		public String url;
	}
	
}
