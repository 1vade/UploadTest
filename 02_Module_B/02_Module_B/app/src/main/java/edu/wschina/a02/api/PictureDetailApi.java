package edu.wschina.a02.api;

import android.content.Context;

import java.lang.reflect.Constructor;

import edu.wschina.a02.fragment.HomeFragment;
import edu.wschina.a02.util.HttpUtil;

public class PictureDetailApi {
	public static void get(Context context, int id, HttpUtil.Callback<ResponseMo> callback) {
		HttpUtil.get(context, "/picture/detail/" + id, null, true, ResponseMo.class, callback);
	}
	
	public static class ResponseMo {
		public int code;
		public String msg;
		public Data data;
		
	}
	
	public static class Data {
		public int id;
		public String url;
		public String title;
		public String time;
		public String description;
	}
}
