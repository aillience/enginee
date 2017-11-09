package com.aillience.enginee.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aillience.enginee.R;
import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.model.bean.UserBean;
import com.aillience.enginee.mvp.presenter.impl.LoginPresenterImpl;
import com.aillience.enginee.mvp.view.ILoginView;
import com.aillience.enginee.ui.base.BaseActivity;
import com.aillience.enginee.util.MLog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 登录界面
 */

public class LoginActivity extends BaseActivity implements ILoginView, RequestCallBack<UserBean> {

    @BindView(R.id.edt_login_username)
    EditText username;
    @BindView(R.id.edt_login_password)
    EditText password;
    @Inject
    LoginPresenterImpl loginPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void injectAction() {
        if (mActivityComponent != null) {
            mActivityComponent.inject(this);
            String nn = mActivityComponent.getActivity().getLocalClassName();
            MLog.d("包名" + nn);
        }
    }

    @Override
    public String getUserName() {
        return username.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return password.getText().toString().trim();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
    @Override
    public void showMsg(String message) {
        MLog.d(message);
    }


    @OnClick({R.id.bt_register, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                finish();
                break;
            case R.id.bt_login:
                Map<String, String> map = new HashMap<>();
                map.put("method", "Disembark");
                map.put("userName", getUserName());
                map.put("pwd", getPwd());
                loginPresenter.Login(this, map);
                break;
        }
    }

    @Override
    public void beforeRequest() {
        MLog.i("开始请求啦");

    }

    @Override
    public void success(UserBean data) {
        MLog.i(data.getMessage());
        mToast.show(data.getMessage());
        if (data.getStatus() == 1) {
            MLog.d(data.getData().get(0).getUserName() + "登录成功");
        }
    }

    @Override
    public void onError(String errorMsg) {
        MLog.e(errorMsg);
    }
}
