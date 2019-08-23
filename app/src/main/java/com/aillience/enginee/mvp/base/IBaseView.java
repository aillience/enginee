package com.aillience.enginee.mvp.base;


import com.yfl.library.base.BaseViewHolder;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: View接口
 */

public interface IBaseView {

    void showProgress();

    void hideProgress();

    void showMsg(String message);

    //新增一个绑定界面自定义布局view的接口
    BaseViewHolder bindOwnView();
}
