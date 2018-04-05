package com.example.catagorydemo.base;

/**
 * Created by dell on 2018-04-04  17:05
 */

public interface OnNetListener<T> {
    void onSuccess(T t);

    void onFailure(Throwable throwable);
}
