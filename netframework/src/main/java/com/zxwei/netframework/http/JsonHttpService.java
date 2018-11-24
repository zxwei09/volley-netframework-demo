package com.zxwei.netframework.http;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zxwei on 2018/11/23.
 */

public class JsonHttpService implements IHttpService {

    private String url;
    private byte[] requestData;
    IHttpListener httpListener;


    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] data) {
        this.requestData = data;
    }


    @Override
    public void setHttpCallBack(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    @Override
    public void execute() {

        HttpURLConnection urlConnection = null;
        URL url = null;
        try {
            url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(6000);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setReadTimeout(3000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Tyoe", "application/json;charect:utf-8");
            urlConnection.connect();
            OutputStream out = urlConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            if(requestData != null) {
                bos.write(requestData);
            }
            bos.flush();
            out.close();
            bos.close();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                httpListener.onSuccess(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            httpListener.onFailure();
        } catch (IOException e) {
            e.printStackTrace();
            httpListener.onFailure();
        } finally {
            urlConnection.disconnect();
        }
    }


}
