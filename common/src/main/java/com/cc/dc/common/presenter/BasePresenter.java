package com.cc.dc.common.presenter;

/**
 * Created by dc on 2017/9/18.
 */
public abstract class BasePresenter<T, E> {

    protected T view;

    protected E model;

    public void setVM(T view, E model) {
        this.view = view;
        this.model = model;
    }
}
