package com.aillience.enginee.mvp.base;

import android.support.annotation.NonNull;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 作用释义
 */

public interface IBasePresenter {
    void onCreate();

    void attachView(@NonNull IBaseView view);//传递view

    void onDestroy();
}
