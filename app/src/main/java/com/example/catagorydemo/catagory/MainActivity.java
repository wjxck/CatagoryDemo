package com.example.catagorydemo.catagory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.catagorydemo.R;
import com.example.catagorydemo.adapter.LeftAdapter;
import com.example.catagorydemo.adapter.RightAdapter;
import com.example.catagorydemo.bean.CatagoryBean;
import com.example.catagorydemo.bean.ProductCatagoryBean;
import com.example.catagorydemo.utils.GlideImageLoader;
import com.example.catagorydemo.utils.MyExpanableListView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClassContract.IClassView {

    private ListView mLvLeft;
    private Banner mBanner;
    private MyExpanableListView mElv;
    private LeftAdapter adapter;
    private List<String> groupList = new ArrayList<>();//一级列表数据
    private List<List<ProductCatagoryBean.DataBean.ListBean>> childList = new ArrayList<>();//二级列表数据
    private ClassPresenter classPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        classPresenter = new ClassPresenter(this);
        //去P层，调用getCatagory
        classPresenter.getCatagoty();

    }

    private void initView() {
        mLvLeft = (ListView) findViewById(R.id.lv_left);
        mBanner = (Banner) findViewById(R.id.banner);
        mElv = (MyExpanableListView) findViewById(R.id.elv);

        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> images = new ArrayList<>();
        images.add("http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg");
        images.add("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg");
        images.add("http://pic.58pic.com/58pic/13/74/51/99d58PIC6vm_1024.jpg");
        mBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void showData(List<CatagoryBean.DataBean> list) {
        //创建适配器
        adapter = new LeftAdapter(this, list);
        mLvLeft.setAdapter(adapter);
        //设置默认展示
        adapter.press(0);
        //给listview 设置点击事件
        mLvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.press(position);
                //请求对应的右侧数据
                //先获取cid
                CatagoryBean.DataBean dataBean = (CatagoryBean.DataBean) parent.getItemAtPosition(position);
                int cid = dataBean.getCid();
                classPresenter.getProfuctCatagory(cid + "");
            }
        });
    }

    @Override
    public void showElvData(List<ProductCatagoryBean.DataBean> list) {
        groupList.clear();
        childList.clear();
        //给二级列表封住数据
        for (int i = 0; i < list.size(); i++) {
            ProductCatagoryBean.DataBean dataBean = list.get(i);
            groupList.add(dataBean.getName());
            childList.add(dataBean.getList());
        }
        //创建适配器
        RightAdapter rightAdapter = new RightAdapter(this, groupList, childList);
        mElv.setAdapter(rightAdapter);
        //设置默认全部展开
        for (int i = 0; i < list.size(); i++) {
            mElv.expandGroup(i);
        }
        //设置父节点(章目录)不可点击
        mElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //返回true,表示不可点击
                return true;
            }
        });
    }

}
