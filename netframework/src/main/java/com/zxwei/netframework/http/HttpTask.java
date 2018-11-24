package com.zxwei.netframework.http;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by zxwei on 2018/11/23.
 */

public class HttpTask<T> implements Runnable {
    private IHttpListener httpListener;
    private IHttpService httpService;

    public<T> HttpTask(T requeatInfo, String url,IHttpService httpService, IHttpListener httpListener){
        this.httpService = httpService;
        this.httpListener = httpListener;
        httpService.setUrl(url);
        httpService.setHttpCallBack(httpListener);
        if(requeatInfo != null) {
            String content = JSON.toJSON(requeatInfo).toString();
            try {
                httpService.setRequestData(content.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
          httpService.execute();
    }
}
