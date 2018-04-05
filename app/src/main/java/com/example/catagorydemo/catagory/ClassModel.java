package com.example.catagorydemo.catagory;

import com.example.catagorydemo.base.OnNetListener;
import com.example.catagorydemo.bean.CatagoryBean;
import com.example.catagorydemo.bean.ProductCatagoryBean;
import com.example.catagorydemo.net.RetrofitHelper;
import com.example.catagorydemo.net.ServiceApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2018-04-04  17:06
 */

public class ClassModel implements ClassContract.IClassModel {
    @Override
    public void getCatagory(final OnNetListener<CatagoryBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServiceApi();
        serviceApi.getCatagory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CatagoryBean>() {
                    @Override
                    public void accept(CatagoryBean catagoryBean) throws Exception {
                        onNetListener.onSuccess(catagoryBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure((Exception) throwable);
                    }
                });
    }

    @Override
    public void getProductCatagory(String cid, final OnNetListener<ProductCatagoryBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServiceApi();
        serviceApi.getProductCatagory(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductCatagoryBean>() {
                    @Override
                    public void accept(ProductCatagoryBean productCatagoryBean) throws Exception {
                        onNetListener.onSuccess(productCatagoryBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure((Exception) throwable);
                    }
                });
    }
}
