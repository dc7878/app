package com.cc.dc.customview.delegate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.aliyun.vodplayerview.widget.AliyunVodPlayerView;
import com.cc.dc.common.utils.LUtil;
import com.cc.dc.customview.R;
import com.cc.dc.customview.delegate.bean.NewsEntity;
import com.cc.dc.customview.delegate.bean.NewsVideoEntity;
import com.cc.dc.customview.view.CustomLinearLayout;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by dc on 2018/2/28.
 * 新闻默认的Adapter
 */

public class NewsVideoAdapter extends AbsListItemAdapterDelegate<NewsVideoEntity, NewsEntity, NewsVideoAdapter.NormalViewHolder> {

    private LayoutInflater inflater;

    private Context context;

    public NewsVideoAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    protected boolean isForViewType(@NonNull NewsEntity item, @NonNull List<NewsEntity> items, int position) {
        return item instanceof NewsVideoEntity;
    }

    @NonNull
    @Override
    protected NormalViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_list_test_player, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsVideoEntity item, @NonNull final NormalViewHolder viewHolder, @NonNull List<Object> payloads) {
        int type = 1;
        if (type == 1) {
            viewHolder.tvTitle.setVisibility(View.GONE);
            viewHolder.layerPlayer.setVisibility(View.VISIBLE);
            AliyunLocalSource.AliyunLocalSourceBuilder builder = new AliyunLocalSource.AliyunLocalSourceBuilder();
            builder.setSource("http://wvideo.spriteapp.cn/video/2018/0318/ffaf1d68-2a57-11e8-be56-1866daeb0df1_wpd.mp4");
            AliyunLocalSource localSource = builder.build();
            viewHolder.playerView.setLocalSource(localSource);
            viewHolder.playerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            viewHolder.playerView.setOnPreparedListener(new IAliyunVodPlayer.OnPreparedListener() {
                @Override
                public void onPrepared() {
                    //准备完成时触发
//                    viewHolder.playerView.start();
                    Log.e("TestPlayerAdapter", "onPrepared");
                }
            });
            viewHolder.playerView.setOnCompletionListener(new IAliyunVodPlayer.OnCompletionListener() {
                @Override
                public void onCompletion() {
                    //播放正常完成时触发
                    Log.e("TestPlayerAdapter", "onCompletion");
                }
            });
            viewHolder.playerView.setOnFirstFrameStartListener(new IAliyunVodPlayer.OnFirstFrameStartListener() {
                @Override
                public void onFirstFrameStart() {
                    //首帧显示时触发
                    Log.e("TestPlayerAdapter", "onFirstFrameStart");
                }
            });
            viewHolder.playerView.setOnChangeQualityListener(new IAliyunVodPlayer.OnChangeQualityListener() {
                @Override
                public void onChangeQualitySuccess(String finalQuality) {
                    //清晰度切换成功时触发
                    Log.e("TestPlayerAdapter", "onChangeQualitySuccess");
                }
                @Override
                public void onChangeQualityFail(int code, String msg) {
                    //清晰度切换失败时触发
                    Log.e("TestPlayerAdapter", "onChangeQualityFail");
                }
            });
            viewHolder.playerView.setOnStoppedListner(new IAliyunVodPlayer.OnStoppedListener() {
                @Override
                public void onStopped() {
                    //使用stop接口时触发
                    Log.e("TestPlayerAdapter", "onStopped");
                }
            });
            viewHolder.playerView.setOnCircleStartListener(new IAliyunVodPlayer.OnCircleStartListener() {
                @Override
                public void onCircleStart() {
                    //循环播放开始
                    Log.e("TestPlayerAdapter", "onPrepared");
                }
            });
            viewHolder.playerView.setOnInfoListener(new IAliyunVodPlayer.OnInfoListener() {
                @Override
                public void onInfo(int i, int i1) {
                    Log.e("TestPlayerAdapter", "onInfo");
                }
            });
            viewHolder.playerView.setOnLoadingListener(new IAliyunVodPlayer.OnLoadingListener() {
                @Override
                public void onLoadStart() {
                    Log.e("TestPlayerAdapter", "onLoadStart");
                }

                @Override
                public void onLoadEnd() {
                    Log.e("TestPlayerAdapter", "onLoadEnd");
                }

                @Override
                public void onLoadProgress(int i) {
                    Log.e("TestPlayerAdapter", "onLoadProgress");
                }
            });
            viewHolder.playerView.setOnBufferingUpdateListener(new IAliyunVodPlayer.OnBufferingUpdateListener() {

                @Override
                public void onBufferingUpdate(int i) {
                    Log.e("TestPlayerAdapter", "onBufferingUpdate");
                }
            });
//            holder.playerView.start();
            viewHolder.imageViewPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.playerView.start();
                }
            });
        } else {
            viewHolder.tvTitle.setVisibility(View.VISIBLE);
            viewHolder.playerView.setVisibility(View.GONE);
            LUtil.e("TestPlayerAdapter", "222");
        }
        viewHolder.tvTitle.setText(item.title);
    }

    static class NormalViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.layer_player)
        CustomLinearLayout layerPlayer;
        @Bind(R.id.player_view)
        AliyunVodPlayerView playerView;
        @Bind(R.id.iv_play)
        ImageView imageViewPlay;

        private NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
