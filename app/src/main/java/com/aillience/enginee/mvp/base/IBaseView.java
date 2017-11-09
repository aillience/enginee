package com.aillience.enginee.mvp.base;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: View接口
 */

public interface IBaseView {

    void showProgress();

    void hideProgress();

    void showMsg(String message);
}
