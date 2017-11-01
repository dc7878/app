package com.cc.dc.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cc.dc.bean.VideoBean;
import com.cc.dc.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/9/20.
 */
public class RecyclerViewAdapterWithFooter extends RecyclerView.Adapter {

    private List<VideoBean> data;

    private LayoutInflater inflater;

    private final int TYPE_NORMAL = 0;
    private final int TYPE_LOAD_MORE = 1;

    public RecyclerViewAdapterWithFooter(Context context, List<VideoBean> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_NORMAL:
                view = inflater.inflate(R.layout.item_fragment_home, parent, false);
                holder = new VideoViewHolder(view);
                break;
            case TYPE_LOAD_MORE:
                view = inflater.inflate(R.layout.recycler_view_footer, parent, false);
                holder = new LoadMoreViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case TYPE_NORMAL:
                VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
                videoViewHolder.tvName.setText(data.get(position).getName());
                videoViewHolder.tvDesc.setText(data.get(position).getDesc());
                break;
            case TYPE_LOAD_MORE:
                LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) holder;
                loadMoreViewHolder.itemView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        VideoBean bean = data.get(position);
        if (null == bean) {
            return TYPE_LOAD_MORE;
        }
        return TYPE_NORMAL;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_desc)
        TextView tvDesc;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        private View itemView;

        private ProgressBar progressBar;

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }
}
