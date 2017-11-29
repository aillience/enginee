package com.aillience.enginee.mvp.presenter;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.base.IBasePresenter;
import com.aillience.enginee.mvp.model.bean.ExpressBean;

import java.util.Map;

/**
 * Happy every day.
 * Created by yfl on 2017/9/13 0013
 * explain: 快递查询的
 * @author yfl
 */

public interface IExpressPresenter extends IBasePresenter{
    /**search Express 快递查询
     * @param callBack  接口回调
     * @param queryMap  条件参数
     */
    void searchExpress(RequestCallBack<ExpressBean> callBack, Map<String,String> queryMap);
}
