package com.syh.app.butterknifedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends BaseAdapter {

    List<String> mDatas = new ArrayList<>();
    Context mContext;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public String getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        mDatas.clear();
    }

    public void add(String msg) {
        mDatas.add(msg);
    }

    private void remove(int index) {
        mDatas.remove(index);
    }

    private void addAll(List<String> datas) {
        mDatas.addAll(datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            viewHolder = new ViewHolder();
            ButterKnife.bind(viewHolder, convertView);//绑定holder与convertView  在适配器无需解绑
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_itemmsg.setText(getItem(position));

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_itemmsg)
        TextView tv_itemmsg;
    }
}
