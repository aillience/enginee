package com.aillience.enginee.mvp.base;

import android.support.annotation.NonNull;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.util.MLog;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: Presenter接口实现基类
 */

public class BasePresenterImpl<T extends IBaseView,E> implements IBasePresenter,RequestCallBack<E>{
    private T mView;
    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(@NonNull IBaseView view) {
        try {
            mView = (T) view;
        }catch (Exception e){
            mView=null;
        }
    }

    @Override
    public void onDestroy() {

    }
    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void success(E data) {
        mView.hideProgress();
    }

    @Override
    public void onError(String errorMsg) {
        mView.hideProgress();
        mView.showMsg(errorMsg);
    }
    protected boolean before(){//默认通过
        return true;
    }
    protected void after(Object o){
        MLog.i(o.toString());
    }


}
