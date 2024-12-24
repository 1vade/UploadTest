package com.example.apitest.Util;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Http {
    private static final String ip = "https://v.juhe.cn/toutiao/index?typpe=";

    public static JSONObject POST_P(String path, String jput) throws Exception {
        String[] body = {null};
        Thread thread = new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jput);
            Request request = new Request.Builder()
                    .url(ip + path)
                    .post(requestBody)
                    .build();
            try {
                body[0] = client.newCall(request).execute().body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        return new JSONObject(body[0]);
    }

    public static JSONObject GET(String path) throws Exception {
        String[] body = {null};
        Thread thread = new Thread(() -> {
            OkHttpClient client = new OkHttpClient().newBuilder().build();

            Request request = new Request.Builder()
                    .url(ip + path)
                    .method("GET", null)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                body[0] = response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        return new JSONObject(body[0]);
    }
}
