package com.cc.dc.customview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.aliyun.vodplayerview.widget.AliyunVodPlayerView;
import com.cc.dc.customview.R;
import com.cc.dc.customview.bean.TestPlayerBean;
import com.cc.dc.customview.view.CustomLinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/12/20.
 */

public class TestPlayerAdapter extends RecyclerView.Adapter<TestPlayerAdapter.PlayerHolder> {

    private Context context;

    private List<TestPlayerBean> data;

    private OnItemClickListener listener;

    public TestPlayerAdapter(Context context, List<TestPlayerBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_test_player, parent, false);
        PlayerHolder holder = new PlayerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PlayerHolder holder, int position) {
        int type = data.get(position).getType();
        if (type == 1) {
            holder.tvTitle.setVisibility(View.GONE);
            holder.playerView.setVisibility(View.VISIBLE);
            AliyunLocalSource.AliyunLocalSourceBuilder builder = new AliyunLocalSource.AliyunLocalSourceBuilder();
            builder.setSource(data.get(position).getUrl());
            AliyunLocalSource localSource = builder.build();
            holder.playerView.setLocalSource(localSource);
            holder.playerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            holder.playerView.setOnPreparedListener(new IAliyunVodPlayer.OnPreparedListener() {
                @Override
                public void onPrepared() {
                    //准备完成时触发
                    holder.playerView.start();
                }
            });
        } else {
            holder.tvTitle.setVisibility(View.VISIBLE);
            holder.playerView.setVisibility(View.GONE);
        }
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.layerPlayer.setOnClickListener(new ClickListener(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    class ClickListener implements View.OnClickListener {

        private int position;

        public ClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            listener.onItemClickListener(v, position);
        }
    }

    class PlayerHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.layer_player)
        CustomLinearLayout layerPlayer;
        @Bind(R.id.player_view)
        AliyunVodPlayerView playerView;

        View itemView;

        public PlayerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
