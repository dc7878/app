package com.cc.dc.customview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cc.dc.customview.R;
import com.cc.dc.customview.bean.ScrollBean;

import java.util.List;

/**
 * Created by dc on 2017/12/11.
 */

public class ScrollAdapter extends BaseAdapter {

    private List<ScrollBean> data;

    private LayoutInflater inflater;

    private ViewHolder holder;

    public ScrollAdapter(List<ScrollBean> data, Context context) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ScrollBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScrollBean bean = data.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(bean.getName());
        return convertView;
    }

    static class ViewHolder {
        public TextView tvName;
    }
}
