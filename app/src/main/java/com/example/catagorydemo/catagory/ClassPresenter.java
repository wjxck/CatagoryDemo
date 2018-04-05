package com.example.catagorydemo.catagory;

import com.example.catagorydemo.base.OnNetListener;
import com.example.catagorydemo.bean.CatagoryBean;
import com.example.catagorydemo.bean.ProductCatagoryBean;

import java.util.List;

/**
 * Created by dell on 2018-04-04  17:07
 */

public class ClassPresenter implements ClassContract.IClassPresetner{
    private ClassContract.IClassView iClassView;
    private ClassContract.IClassModel iClassModel;

    public ClassPresenter(ClassContract.IClassView iClassView) {
        this.iClassView = iClassView;
        iClassModel = new ClassModel();
    }

    public void getCatagoty() {
        iClassModel.getCatagory(new OnNetListener<CatagoryBean>() {
            @Override
            public void onSuccess(CatagoryBean catagoryBean) {
                iClassView.showData(catagoryBean.getData());
                //拿到右侧第一条数据
                List<CatagoryBean.DataBean> dataBean = catagoryBean.getData();
                int cid = dataBean.get(0).getCid();
                //获取右侧的数据
                getProfuctCatagory(cid + "");
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    public void getProfuctCatagory(String cid) {
        iClassModel.getProductCatagory(cid, new OnNetListener<ProductCatagoryBean>() {
            @Override
            public void onSuccess(ProductCatagoryBean productCatagoryBean) {
                iClassView.showElvData(productCatagoryBean.getData());
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
