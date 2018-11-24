package com.zxwei.netframework.http;

/**
 * Created by zxwei on 2018/11/23.
 */

public interface ICallback<M> {
    void onSuccess(M result);
    void onFailure();
}
