package com.example.catagorydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.catagorydemo.R;
import com.example.catagorydemo.bean.CatagoryBean;

import java.util.List;

public class LeftAdapter extends BaseAdapter {
    private Context context;
    private List<CatagoryBean.DataBean> list;

    public LeftAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.class_left_item, null);
            holder.tv = view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        CatagoryBean.DataBean dataBean = list.get(position);
        holder.tv.setText(dataBean.getName());
        //自己加的属性，用于判断是否选中
        if (dataBean.isChecked()) {
            holder.tv.setBackgroundColor(Color.parseColor("#EEEEEE"));
            holder.tv.setTextColor(Color.parseColor("#FF0000"));
        } else {
            holder.tv.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv.setTextColor(Color.parseColor("#FF262426"));
        }

        return view;
    }

    class ViewHolder {
        TextView tv;
    }

    /**
     * 条目点击变色方法
     *
     * @param position
     */
    public void press(int position) {
        //先遍历循环list集合
        for (int i = 0; i < list.size(); i++) {
            CatagoryBean.DataBean dataBean = list.get(i);
            dataBean.setChecked(false);
        }
        CatagoryBean.DataBean dataBean = list.get(position);
        dataBean.setChecked(true);
        notifyDataSetChanged();
    }
}
