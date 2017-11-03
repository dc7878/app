package com.cc.dc.test;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cc.dc.R;
import com.cc.dc.common.utils.LUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/11/2.
 */
public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<TestBean> data;

    private LayoutInflater inflater;

    public TestAdapter(Context context, List<TestBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TestBean.TYPE.TYPE_TITLE:
                view = inflater.inflate(R.layout.item_fragment_test_title, parent, false);
                viewHolder = new TitleViewHolder(view);
                break;
            case TestBean.TYPE.TYPE_IMAGE:
                view = inflater.inflate(R.layout.item_fragment_test_image, parent, false);
                viewHolder = new ImageViewHolder(view);
                break;
            case TestBean.TYPE.TYPE_MORE:
                view = inflater.inflate(R.layout.item_fragment_test_more, parent, false);
                viewHolder = new MoreViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TestBean bean = data.get(position);
        switch (getItemViewType(position)) {
            case TestBean.TYPE.TYPE_TITLE:
                TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                titleViewHolder.tvTitle.setText(bean.getTitle());
                break;
            case TestBean.TYPE.TYPE_IMAGE:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
//                imageViewHolder.imageView.setImageResource(bean.getImageId());
                Glide.with(context).load(bean.getImageUrl()).into(imageViewHolder.imageView);
                break;
            case TestBean.TYPE.TYPE_MORE:
                MoreViewHolder moreViewHolder = (MoreViewHolder) holder;
                moreViewHolder.tvMore.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        TextView tvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_image)
        CustomImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_more)
        TextView tvMore;

        public MoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
