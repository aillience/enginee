package com.aillience.enginee.ui.main;

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
import com.aillience.enginee.service.LivenHeartService;
import com.aillience.enginee.ui.base.BaseActivity;
import com.aillience.enginee.ui.express.ExpressActivity;
import com.aillience.enginee.ui.list.PartListActivity;
import com.aillience.enginee.ui.login.LoginActivity;
import com.aillience.enginee.ui.main.adapter.MainAdapter;
import com.aillience.enginee.util.BasicParameters;
import com.aillience.enginee.util.MyLog;
import com.aillience.enginee.util.TransformUtils;
import com.yfl.library.base.adapter.BaseRecyclerAdapter;
import com.yfl.library.recycler.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 主页
 * @author yfl
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_view)
    MyRecyclerView recyclerView;

    private long time = 0;
    private Intent serviceIntent=null;

    MainAdapter mainAdapter;
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        serviceIntent=new Intent(this,LivenHeartService.class);
        initAdapter();
    }

    @Override
    protected void injectAction() {

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

    private void initAdapter(){
        List<String> list = new ArrayList<>();
        list.add((String) getResources().getText(R.string.search));
        list.add((String) getResources().getText(R.string.login));
        list.add((String) getResources().getText(R.string.joke));
        list.add((String) getResources().getText(R.string.list));
        list.add((String) getResources().getText(R.string.startService));
        list.add((String) getResources().getText(R.string.stopService));
        mainAdapter = new MainAdapter(this,list);
        recyclerView.setGridLayoutManager(4,true);
        recyclerView.setAdapter(mainAdapter);
        mainAdapter.setViewClick(new BaseRecyclerAdapter.viewClick<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                click(item);
            }

            @Override
            public void onItemLongClick(View view, String item, int position) {

            }
        });
    }

    private void click(String item){
        Intent intent = null;
        if(item.contentEquals(getResources().getText(R.string.search))){
            intent = new Intent(this, ExpressActivity.class);
        }else if(item.contentEquals(getResources().getText(R.string.login))){
            startActivityByName(LoginActivity.class.getCanonicalName(), false);
        }else if(item.contentEquals(getResources().getText(R.string.joke))){
            if (fastClick()) {
                getJoke();
            }
        }else if(item.contentEquals(getResources().getText(R.string.list))){
            //列表
            intent = new Intent(this, PartListActivity.class);
        }else if(item.contentEquals(getResources().getText(R.string.startService))){
            mHandler.sendEmptyMessage(2);
        }else if(item.contentEquals(getResources().getText(R.string.stopService))){
            mHandler.sendEmptyMessage(3);
        }
        if (intent != null){
            startActivity(intent);
        }
    }

    private void getJoke() {
        new RetrofitManager(NetWorkUrl.URL_JOKE).getJokeList()
                .compose(TransformUtils.defaultSchedulers())
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
