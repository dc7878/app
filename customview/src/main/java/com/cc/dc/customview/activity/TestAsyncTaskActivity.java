package com.cc.dc.customview.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dc on 2017/11/24.
 */

public class TestAsyncTaskActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("TestAsyncTaskActivity", Runtime.getRuntime().availableProcessors() + "");

        for (int i = 0; i < 2000;i ++) {
            new MyAsyncTask().execute("info>>>" + i);
        }
    }

    class MyAsyncTask extends AsyncTask<String, Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("TestAsyncTaskActivity", "onPreExecute-->" + Thread.currentThread().getName());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("TestAsyncTaskActivity", "onPostExecute-->" + Thread.currentThread().getName());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e("TestAsyncTaskActivity", "onProgressUpdate-->" + Thread.currentThread().getName());
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.e("TestAsyncTaskActivity", "doInBackground-->" + strings[0] );
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "doInBackground info";
        }
    }
}
