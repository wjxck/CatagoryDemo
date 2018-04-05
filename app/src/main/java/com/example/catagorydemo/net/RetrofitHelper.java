package com.example.catagorydemo.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2018-04-04  16:51
 */

public class RetrofitHelper {
    private static Retrofit retrofit;
    private static ServiceApi serviceApi;

    /**
     * 初始化Retrofit 单例模式
     */
    private static Retrofit getRetrofit() {
        //线程安全
        if (retrofit == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofit == null) {
                    //设置拦截器
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //初始化OkHttpClient
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(logging)//添加拦截器
                            .addInterceptor(new MyInterceptor())
                            .connectTimeout(50, TimeUnit.SECONDS)//设置连接超时
                            .build();

                    //初始化Retrofit
                    retrofit = new Retrofit.Builder()
                            .baseUrl(UrlsApi.BASE_URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }

        return retrofit;
    }


    public static ServiceApi getServiceApi() {
        if (serviceApi == null) {
            synchronized (RetrofitHelper.class) {
                if (serviceApi == null) {
                    serviceApi = getRetrofit().create(ServiceApi.class);
                }
            }
        }
        return serviceApi;
    }

    /**
     * 添加公共参数拦截器
     */
    static class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl httpUrl = request
                    .url()
                    .newBuilder()
                    .addQueryParameter("source", "android")
                    .build();
            Request requestNew = request
                    .newBuilder()
                    .url(httpUrl)
                    .build();
            return chain.proceed(requestNew);
        }
    }
}
