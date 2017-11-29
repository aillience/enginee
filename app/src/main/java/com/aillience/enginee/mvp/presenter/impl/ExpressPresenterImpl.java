package com.aillience.enginee.mvp.presenter.impl;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.base.BasePresenterImpl;
import com.aillience.enginee.mvp.model.bean.ExpressBean;
import com.aillience.enginee.mvp.presenter.IExpressPresenter;
import com.aillience.enginee.mvp.view.IExpressView;
import com.aillience.enginee.net.NetWorkUrl;
import com.aillience.enginee.net.NetWorkUtil;
import com.aillience.enginee.net.RetrofitManager;
import com.aillience.enginee.util.MyLog;
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
 * @author yfl
 */

public class ExpressPresenterImpl extends BasePresenterImpl<IExpressView,ExpressBean> implements IExpressPresenter{
    @Inject
    ExpressPresenterImpl(){

    }

    @Override
    protected boolean before() {
        //判断是否通过
        return super.before();
    }

    @Override
    protected void after(Object o) {
        super.after(o);
    }

    @Override
    public void searchExpress(final RequestCallBack<ExpressBean> callBack, Map<String, String> queryMap) {
        if(before()){
            new RetrofitManager(NetWorkUrl.URL_EXPRESS).getExpress(queryMap)
                .compose(TransformUtils.<ExpressBean>defaultSchedulers())
                .subscribe(new Observer<ExpressBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        MyLog.d("开始请求");
                        callBack.beforeRequest();
                    }

                    @Override
                    public void onNext(@NonNull ExpressBean bean) {
                        MyLog.d("返回接口成功");
                        callBack.success(bean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        MyLog.e("返回接口失败");
                        callBack.onError(NetWorkUtil.analyzeNetworkError(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        }else {
            after("网络已断开……");
        }
    }
}
