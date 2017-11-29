package com.aillience.enginee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.aillience.enginee.R;
import com.aillience.enginee.app.MyApp;
import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.model.entity.JokeEntity;
import com.aillience.enginee.net.NetWorkUrl;
import com.aillience.enginee.net.NetWorkUtil;
import com.aillience.enginee.net.RetrofitManager;
import com.aillience.enginee.service.LivenHeartService;
import com.aillience.enginee.ui.base.BaseActivity;
import com.aillience.enginee.util.BasicParameters;
import com.aillience.enginee.util.MyLog;
import com.aillience.enginee.util.TransformUtils;

import java.util.List;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 主页
 * @author yfl
 */
public class MainActivity extends BaseActivity {
    private long time = 0;
    private Intent serviceIntent=null;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceIntent=new Intent(this,LivenHeartService.class);
    }

    @Override
    protected void onDestroy() {
        if(serviceIntent!=null){
            stopService(serviceIntent);
        }
        super.onDestroy();
    }

    @Override
    protected boolean handleCallback(Message msg) {
        switch (msg.what) {
            case 1:
                mToast.show("Handler消息来了");
                break;
            case 2:
                if(serviceIntent!=null){
                    startService(serviceIntent);
                }
                break;
            case 3:
                if(serviceIntent!=null){
                    stopService(serviceIntent);
                }
                break;
            default:break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - time > BasicParameters.LONG_thousand)) {
                mToast.show("再按一次退出程序", BasicParameters.LONG_thousand);
                time = System.currentTimeMillis();
            } else {
                MyApp.getActManager().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.Btn_Search, R.id.Btn_Login, R.id.Btn_Joke,R.id.Btn_startService, R.id.Btn_stopService})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.Btn_Search:
                intent = new Intent(this, ExpressActivity.class);
                break;
            case R.id.Btn_Login:
                startActivityByName(LoginActivity.class.getCanonicalName(), false);
                break;
            case R.id.Btn_Joke:
                if (fastClick()) {
                    getJoke();
                }
                break;
            case R.id.Btn_startService:
                mHandler.sendEmptyMessage(2);
                break;
            case R.id.Btn_stopService:
                mHandler.sendEmptyMessage(3);
                break;
            default:break;
        }
        if (intent != null){
            startActivity(intent);
        }
    }

    private void getJoke() {
        new RetrofitManager(NetWorkUrl.URL_JOKE).getJokeList()
                .compose(TransformUtils.<List<JokeEntity>>defaultSchedulers())
                .subscribe(new Observer<List<JokeEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<JokeEntity> jokeBean) {
                        MyLog.d("返回接口成功");
                        requestCallBack.success(jokeBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        MyLog.e("返回接口失败");
                        requestCallBack.onError(NetWorkUtil.analyzeNetworkError(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private RequestCallBack<List<JokeEntity>> requestCallBack = new RequestCallBack<List<JokeEntity>>() {
        @Override
        public void beforeRequest() {

        }

        @Override
        public void success(List<JokeEntity> data) {
            mHandler.sendEmptyMessage(1);
            MyLog.i("笑话获取成功");
            MyLog.i("笑话：" + data.get(0).getContent());
        }

        @Override
        public void onError(String errorMsg) {

        }
    };

}
