package com.aillience.enginee.mvp.presenter.impl;

import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.base.BasePresenterImpl;
import com.aillience.enginee.mvp.model.bean.UserBean;
import com.aillience.enginee.mvp.presenter.ILoginPresenter;
import com.aillience.enginee.mvp.view.ILoginView;
import com.aillience.enginee.net.NetWorkUrl;
import com.aillience.enginee.net.NetWorkUtil;
import com.aillience.enginee.net.RetrofitManager;
import com.aillience.enginee.util.MyLog;
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
 * @author yfl
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
    public void login(final RequestCallBack<UserBean> callBack, Map<String, Object> map) {
        if(before()){
            new RetrofitManager(NetWorkUrl.URL_PT).getLogin(map)
                    .compose(TransformUtils.defaultSchedulers())
                    .subscribe(new Observer<UserBean>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            MyLog.d("开始请求");
                            callBack.beforeRequest();
                        }

                        @Override
                        public void onNext(@NonNull UserBean bean) {
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
