package com.cc.dc.kotlindemo;

import com.cc.dc.kotlindemo.net.HttpCallBack;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by dc on 2018/6/1.
 */

public class Test {

    public static <T> Object invokeStaticMethod(String className, String methodName,T clazz,
                                     Object[] args) throws Exception {

        HttpCallBack<T> callBack = new HttpCallBack<T>() {
            @Override
            public void onStart(@NotNull Disposable disposable) {

            }

            @Override
            public void onResult(T result) {

            }

            @Override
            public void onError(@NotNull String msg) {

            }
        };

        Class ownerClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(null, args);
    }

    public static void test(){
        try {
            Test.invokeStaticMethod("1", "2", List.class, new Object[2]);
        } catch (Exception e) {

        }
    }
}
