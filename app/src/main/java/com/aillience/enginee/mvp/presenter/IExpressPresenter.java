package com.aillience.enginee.mvp.presenter;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.base.IBasePresenter;
import com.aillience.enginee.mvp.model.bean.ExpressBean;

import java.util.Map;

/**
 * Happy every day.
 * Created by yfl on 2017/9/13 0013
 * explain: 快递查询的
 */

public interface IExpressPresenter extends IBasePresenter{
    void SearchExpress(RequestCallBack<ExpressBean> callBack, Map<String,String> queryMap);
}
