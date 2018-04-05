package com.example.catagorydemo.net;

import com.example.catagorydemo.bean.CatagoryBean;
import com.example.catagorydemo.bean.ProductCatagoryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dell on 2018-04-04  16:50
 */

public interface ServiceApi {
    @GET(UrlsApi.CATAGORY)
    Observable<CatagoryBean> getCatagory();

    @GET(UrlsApi.PRODUCTCATAGORY)
    Observable<ProductCatagoryBean> getProductCatagory(@Query("cid") String cid);
}
