package com.aillience.enginee.mvp.presenter.impl;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.base.BasePresenterImpl;
import com.aillience.enginee.mvp.model.bean.UserBean;
import com.aillience.enginee.mvp.presenter.ILoginPresenter;
import com.aillience.enginee.mvp.view.ILoginView;
import com.aillience.enginee.net.NetWorkUrl;
import com.aillience.enginee.net.NetWorkUtil;
import com.aillience.enginee.net.RetrofitManager;
import com.aillience.enginee.util.MLog;
import com.aillience.enginee.util.NetUtil;
import com.aillience.enginee.util.TransformUtils;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 登录接口实现类
 */

public class LoginPresenterImpl extends BasePresenterImpl<ILoginView,UserBean> implements ILoginPresenter{
    @Inject
    LoginPresenterImpl(){

    }

    @Override
    protected boolean before() {
        return NetUtil.isNetworkAvailable();
    }

    @Override
    public void Login(final RequestCallBack<UserBean> callBack, Map<String, String> map) {
        if(before()){
            new RetrofitManager(NetWorkUrl.URL_PT).getLogin(map)
                    .compose(TransformUtils.<UserBean>defaultSchedulers())
                    .subscribe(new Observer<UserBean>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            MLog.d("开始请求");
                            callBack.beforeRequest();
                        }

                        @Override
                        public void onNext(@NonNull UserBean bean) {
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
        }else {
            after("网络已断开……");
        }
    }
}
