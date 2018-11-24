package com.zxwei.netframework.http;

/**
 * Created by zxwei on 2018/11/24.
 */

public class Volley {


    public static<T,M> void sendJsonRequest(T requestInfo, String url, Class<M> response, ICallback callback) {
        IHttpService service = new JsonHttpService();
        IHttpListener listener = new JsonHttpListener(response, callback);
        HttpTask<T> httpTask = new HttpTask<T>(requestInfo, url, service, listener);
        ThreadPoolManage.getInstance().execute(httpTask);
    }


}
