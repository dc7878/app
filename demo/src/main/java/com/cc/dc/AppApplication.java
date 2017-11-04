package com.cc.dc;

import android.app.Application;

import com.alivc.player.AliVcMediaPlayer;

/**
 * Created by dc on 2017/9/25.
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AliVcMediaPlayer.init(getApplicationContext(), "");
    }
}
