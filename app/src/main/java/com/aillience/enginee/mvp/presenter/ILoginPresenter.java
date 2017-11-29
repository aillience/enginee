package com.aillience.enginee.mvp.presenter;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.base.IBasePresenter;
import com.aillience.enginee.mvp.model.bean.UserBean;

import java.util.Map;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 作用释义
 * @author yfl
 */

public interface ILoginPresenter extends IBasePresenter {
    /**
     * login
     * @param callBack 回调
     * @param map 条件参数map集
     * 登录接口
     */
    void login(RequestCallBack<UserBean> callBack, Map<String,String> map);
}
