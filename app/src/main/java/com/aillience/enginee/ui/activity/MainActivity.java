package com.aillience.enginee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

import com.aillience.enginee.R;
import com.aillience.enginee.app.MyApp;
import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.model.entity.JokeEntity;
import com.aillience.enginee.net.NetWorkUrl;
import com.aillience.enginee.net.NetWorkUtil;
import com.aillience.enginee.net.RetrofitManager;
import com.aillience.enginee.ui.base.BaseActivity;
import com.aillience.enginee.util.MLog;
import com.aillience.enginee.util.TransformUtils;

import java.util.List;


import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {
    private long time=0;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
    @Override
    protected void injectAction() {

    }
    @Override
    protected boolean handleCallback(Message msg) {
        switch (msg.what){
            case 1:
                mToast.show("Handler消息来了");
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - time > 1000)) {
                mToast.show("再按一次退出程序",1000);
                time = System.currentTimeMillis();
            } else {
                MyApp.getActManager().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.Btn_Search,R.id.Btn_Login,R.id.Btn_Joke})
    public void onViewClicked(View view) {
        Intent intent=null;
        switch (view.getId()){
            case R.id.Btn_Search:
                intent=new Intent(this,ExpressActivity.class);
                break;
            case R.id.Btn_Login:
                startActivityByName(LoginActivity.class.getCanonicalName(),false);
                break;
            case R.id.Btn_Joke:
                if(fastClick())
                getJoke();
                break;
        }
        if(intent!=null)
        startActivity(intent);
    }
    private void getJoke(){
        new RetrofitManager(NetWorkUrl.URL_Joke).getJokeList()
                .compose(TransformUtils.<List<JokeEntity>>defaultSchedulers())
                .subscribe(new Observer<List<JokeEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<JokeEntity> jokeBean) {
                        MLog.d("返回接口成功");
                        requestCallBack.success(jokeBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        MLog.e("返回接口失败");
                        requestCallBack.onError(NetWorkUtil.analyzeNetworkError(e));
                    }
                    @Override
                    public void onComplete() {

                    }
                });

    }
    private RequestCallBack<List<JokeEntity>> requestCallBack=new RequestCallBack<List<JokeEntity>>() {
        @Override
        public void beforeRequest() {

        }
        @Override
        public void success(List<JokeEntity> data) {
            mHandler.sendEmptyMessage(1);
            MLog.i("笑话获取成功");
            MLog.i("笑话："+data.get(0).getContent());
        }

        @Override
        public void onError(String errorMsg) {

        }
    };

}
