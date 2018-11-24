package com.zxwei.netframework.http;

/**
 * Created by zxwei on 2018/11/23.
 */

public interface IHttpService {

    void setUrl(String url);
    void setRequestData(byte[] data);
    void execute();
    void setHttpCallBack(IHttpListener httpListener);

}
