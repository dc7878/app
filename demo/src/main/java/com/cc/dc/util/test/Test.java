package com.cc.dc.util.test;

/**
 * Created by dc on 2017/10/18.
 */

public class Test {
    public static void main(String[] args) {
        Http http = new Http();
        http.doPost("1111", new HttpCallBack() {
            @Override
            public void onResult(String result) {

            }
        });
    }
}
