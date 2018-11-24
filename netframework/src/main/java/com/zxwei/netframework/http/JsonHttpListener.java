package com.zxwei.netframework.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zxwei on 2018/11/23.
 */

public class JsonHttpListener<M> implements IHttpListener {

    Class<M> responseClass;
    ICallback<M> callback;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    public JsonHttpListener(Class<M> requestInfo, ICallback<M> callback) {
        this.responseClass = requestInfo;
        this.callback = callback;
    }


    @Override
    public void onSuccess(InputStream inputStream) {
        String content = getContent(inputStream);
            final M  response = JSONObject.parseObject(content, responseClass);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(callback != null) {
                        callback.onSuccess(response);
                    }
                }
            });

    }

    private String getContent(InputStream inputStream) {
        String content = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  sb.toString();
    }

    @Override
    public void onFailure() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callback != null) {
                    callback.onFailure();
                }
            }
        });

    }
}
