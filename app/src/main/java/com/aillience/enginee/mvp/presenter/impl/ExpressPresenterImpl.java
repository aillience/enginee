package com.aillience.enginee.mvp.presenter.impl;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.base.BasePresenterImpl;
import com.aillience.enginee.mvp.model.bean.ExpressBean;
import com.aillience.enginee.mvp.presenter.IExpressPresenter;
import com.aillience.enginee.mvp.view.IExpressView;
import com.aillience.enginee.net.NetWorkUrl;
import com.aillience.enginee.net.NetWorkUtil;
import com.aillience.enginee.net.RetrofitManager;
import com.aillience.enginee.util.MLog;
import com.aillience.enginee.util.TransformUtils;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Happy every day.
 * Created by yfl on 2017/9/13 0013
 * explain: 快递查询接口实现
 */

public class ExpressPresenterImpl extends BasePresenterImpl<IExpressView,ExpressBean> implements IExpressPresenter{
    @Inject
    ExpressPresenterImpl(){

    }
    @Override
    public void SearchExpress(final RequestCallBack<ExpressBean> callBack, Map<String, String> queryMap) {
        new RetrofitManager(NetWorkUrl.URL_Express).getExpress(queryMap)
                .compose(TransformUtils.<ExpressBean>defaultSchedulers())
                .subscribe(new Observer<ExpressBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        MLog.d("开始请求");
                        callBack.beforeRequest();
                    }

                    @Override
                    public void onNext(@NonNull ExpressBean bean) {
                        MLog.d("返回接口成功");
                        callBack.success(bean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        MLog.e("返回接口失败");
                        callBack.onError(NetWorkUtil.analyzeNetworkError(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
