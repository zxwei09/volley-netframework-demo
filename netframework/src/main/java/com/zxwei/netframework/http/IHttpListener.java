package com.zxwei.netframework.http;

import java.io.InputStream;

/**
 * Created by zxwei on 2018/11/23.
 * 封装响应
 */

public interface IHttpListener {

    void onSuccess(InputStream inputStream);
    void onFailure();



}
