package com.aillience.enginee.mvp.presenter;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.base.IBasePresenter;
import com.aillience.enginee.mvp.model.bean.UserBean;

import java.util.Map;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 作用释义
 */

public interface ILoginPresenter extends IBasePresenter {
    void Login(RequestCallBack<UserBean> callBack, Map<String,String> map);
}
