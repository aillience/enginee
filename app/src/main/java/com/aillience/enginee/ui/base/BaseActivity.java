package com.aillience.enginee.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.aillience.enginee.R;
import com.aillience.enginee.app.MyApp;
import com.aillience.enginee.dagger.component.ActivityComponent;
import com.aillience.enginee.dagger.component.DaggerActivityComponent;
import com.aillience.enginee.dagger.module.ActivityModule;
import com.aillience.enginee.mvp.base.IBasePresenter;
import com.aillience.enginee.mvp.base.IBaseView;
import com.aillience.enginee.util.PublicToast;


import butterknife.ButterKnife;


/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: activity基类
 */

@SuppressWarnings({"JavaDoc", "unused", "deprecation"})
public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView{
    protected ActivityComponent mActivityComponent;
    protected T mIPresenter;
    protected PublicToast mToast;
    protected Activity mActivity;
    protected Context mContext;
    private long lastClick = 0;//最后点击计时
    /**
     * 获取布局ID
     *
     * @return  布局id
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void injectAction();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
    protected Handler mHandler;
    private Handler.Callback  getHandlerCallBack(){
        return new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return handleCallback(msg);
            }
        };
    }
    protected boolean handleCallback(Message msg){
        return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            initView(savedInstanceState);
        }
        MyApp.getActManager().addOneActivity(this);
        mActivity=this;
//        getApplication();
        mContext=getApplicationContext();
        initActivityComponent();
        injectAction();
        mToast=new PublicToast(this);
        if(mIPresenter!=null){
            mIPresenter.onCreate();
        }
        mHandler=new Handler(Looper.myLooper(),getHandlerCallBack());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mIPresenter!=null){
            mIPresenter.attachView(this);
        }
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((MyApp)getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }
    // TODO:适配4.4
    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getActManager().removeOneActivity(this);
        if (mIPresenter != null) {
            mIPresenter.onDestroy();
        }
    }
    @Override
    public void showProgress() {
        //显示加载进度
    }

    @Override
    public void hideProgress() {
        //隐藏加载进度
    }
    @Override
    public void showMsg(String message) {
    }
    //防止快速点击
    protected boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 1000) {
            mToast.show("点的太快啦！");
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * 通过目标activity的包名，跳转页面，尽可能的解除页面跳转间的耦合性
     * @param activityName
     * @param isFinish
     * example:
     * com.zxt.enginee.ui.activity.LoginActivity  自定义String ,极大降低耦合，但是比较繁琐，比如路径或类名改变。
     * LoginActivity.class.getCanonicalName() 通过类方法获取，还是有一定的耦合性。
     */
    protected void startActivityByName(String activityName,boolean isFinish){
        Intent intent=new Intent();
        try {
            Class<?> cls=Class.forName(activityName);
            if(cls!=null){
                intent.setClass(this,cls);
            }
        }catch (ClassNotFoundException e){
            mToast.show("目标页未发现");
        }finally {
            if(intent.getComponent()!=null){
                startActivity(intent);
                if(isFinish){
                    finish();
                }
            }else {
                mToast.show("有点小尴尬");
            }
        }
    }

    /**
     * 通过class 跳转
     * @param cls
     * @param isFinish
     */
    protected void startActivity( Class<?> cls, boolean isFinish){
        try {
            Intent intent=new Intent();
            intent.setClass(this,cls);
            startActivity(intent);
            if(isFinish){
                finish();
            }
        }catch (Exception e){
            mToast.show("跳转出错！");
        }
    }

    /**
     * 通过intent 跳转，主要用于传值。
     * @param intent
     * @param isFinish
     */
    protected void startActivityByIntent(Intent intent,boolean isFinish){
        try {
            if(intent.getComponent()!=null){
                startActivity(intent);
                if(isFinish){
                    finish();
                }
            }
        }catch (Exception e){
            mToast.show("跳转出错！");
        }
    }
}
