package com.cc.dc.ui.common.activity;

import android.os.Bundle;
import android.view.SurfaceView;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer;
import com.cc.dc.R;
import com.cc.dc.common.ui.BaseActivity;
import com.cc.dc.common.utils.LUtil;

import butterknife.Bind;

/**
 * Created by dc on 2017/11/3.
 */
public class PlayVideoActivity extends BaseActivity {

    @Bind(R.id.video_view)
    SurfaceView videoView;

    private AliVcMediaPlayer player;

    @Override
    public int getLayoutId() {
        return R.layout.activity_play_video;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        AliVcMediaPlayer.init(this, "");
        player = new AliVcMediaPlayer(this, videoView);
        initListener();

        // 设置图像适配屏幕，适配最长边
        player.setVideoScalingMode(MediaPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT);
        // 设置图像适配屏幕，适配最短边，超出部分裁剪
        player.setVideoScalingMode(MediaPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);

        //设置缺省编码类型：0表示硬解；1表示软解；
        //如果缺省为硬解，在使用硬解时如果解码失败，会尝试使用软解
        //如果缺省为软解，则一直使用软解，软解较为耗电，建议移动设备尽量使用硬解
        player.setDefaultDecoder(0);
        //如果从历史点开始播放
        //mPlayer.seekTo(position);
        //准备开始播放
        player.prepareAndPlay("http://hlsa.douyucdn.cn/live/625651rqsMVMdsBw_550/playlist.m3u8?wsSecret=c3407ed0543f869a31dd5c1acac68a06&wsTime=1509695874&token=h5-douyu-0-625651-12095f6d9aebdb8d523a8be0682ba332&did=h5_did");
    }

    private void initListener() {
        player.setPreparedListener(new MediaPlayer.MediaPlayerPreparedListener() {
            @Override
            public void onPrepared() {
                LUtil.e("PlayVideoActivity", "onPrepared");
            }
        });

        player.setErrorListener(new MediaPlayer.MediaPlayerErrorListener() {
            @Override
            public void onError(int i, String s) {
                LUtil.e("PlayVideoActivity", "onError>>>" + i + ">>>" + s);
            }
        });

        player.setInfoListener(new MediaPlayer.MediaPlayerInfoListener() {
            @Override
            public void onInfo(int i, int i1) {
                LUtil.e("PlayVideoActivity", "onInfo>>>" + i + ">>>" + i1);
            }
        });

        player.setBufferingUpdateListener(new MediaPlayer.MediaPlayerBufferingUpdateListener() {
            @Override
            public void onBufferingUpdateListener(int i) {
                LUtil.e("PlayVideoActivity", "onBufferingUpdateListener>>>" + i);
            }
        });
    }
}
