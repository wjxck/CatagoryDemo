package com.example.catagorydemo.catagory;

import com.example.catagorydemo.base.OnNetListener;
import com.example.catagorydemo.bean.CatagoryBean;
import com.example.catagorydemo.bean.ProductCatagoryBean;

import java.util.List;

/**
 * Created by dell on 2018-04-04  17:03
 */

public class ClassContract {
    /**
     * V视图层
     */
    interface IClassView {
        void showData(List<CatagoryBean.DataBean> list);

        void showElvData(List<ProductCatagoryBean.DataBean> list);
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IClassPresetner {

    }

    /**
     * M逻辑(业务)处理层
     */
    interface IClassModel {
        void getCatagory(OnNetListener<CatagoryBean> onNetListener);

        void getProductCatagory(String cid, OnNetListener<ProductCatagoryBean> onNetListener);
    }
}
