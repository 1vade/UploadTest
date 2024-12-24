package edu.wschina.a02.api;

import android.content.Context;

import java.util.List;

import edu.wschina.a02.util.HttpUtil;

public class GetHomePicApi {
	public static void getPic(Context context, HttpUtil.Callback<ResponseMo> callback) {
		HttpUtil.get(context, "/picture/all?pageSize=20&pageNumber=1", null, true, ResponseMo.class, callback);
	}
	
	public static class ResponseMo {
		public int code;
		public String msg;
		public Data data;
	}
	
	public static class Data {
		public List<PicMo> list;
		public int total;
	}
	
	public static class PicMo {
		public int id;
		public String url;
	}
}
