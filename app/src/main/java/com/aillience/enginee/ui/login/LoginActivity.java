package com.aillience.enginee.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aillience.enginee.R;
import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.model.bean.UserBean;
import com.aillience.enginee.mvp.presenter.impl.LoginPresenterImpl;
import com.aillience.enginee.mvp.view.ILoginView;
import com.aillience.enginee.ui.base.BaseActivity;
import com.aillience.enginee.util.MyLog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 登录界面
 * @author yfl
 */

public class LoginActivity extends BaseActivity implements ILoginView, RequestCallBack<UserBean> {

    @BindView(R.id.edt_login_username)
    EditText username;
    @BindView(R.id.edt_login_password)
    EditText password;
    @Inject
    LoginPresenterImpl loginPresenter;

    @Override
    protected int getContentViewLayoutId() {
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
            MyLog.d("包名" + nn);
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
        MyLog.d(message);
    }


    @OnClick({R.id.bt_register, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                finish();
                break;
            case R.id.bt_login:
                Map<String, Object> map = new HashMap<>(4);
                map.put("phoneNum",getUserName());
                map.put("pwd",getPwd());
                map.put("msgType",0);
                map.put("captcha","");
                loginPresenter.login(this, map);
                break;
            default:break;
        }
    }

    @Override
    public void beforeRequest() {
        MyLog.i("开始请求啦");
    }

    @Override
    public void success(UserBean data) {
        mToast.show(data.getMsg());
        if (data.getCode() == 200) {
            MyLog.d(data.getData().getCustomerCode() + "登录成功");
        }else {
            MyLog.d( "登录失败，返回code = "+data.getCode());
        }
        MyLog.i("返回 msg = "+data.getMsg());
    }

    @Override
    public void onError(String errorMsg) {
        MyLog.e(errorMsg);
    }
}
