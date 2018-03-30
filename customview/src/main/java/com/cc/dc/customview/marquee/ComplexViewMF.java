package com.cc.dc.customview.marquee;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cc.dc.customview.R;
import com.gongwen.marqueen.MarqueeFactory;

/**
 * Created by dc on 2018/3/29.
 */

public class ComplexViewMF extends MarqueeFactory<LinearLayout, ComplexItemEntity> {

    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    protected LinearLayout generateMarqueeItemView(ComplexItemEntity complexItemEntity) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_mq, null);
        TextView title = layout.findViewById(R.id.tv_title);
        TextView time = layout.findViewById(R.id.tv_time);
        title.setText(complexItemEntity.title);
        time.setText(complexItemEntity.time);
        return layout;
    }
}
