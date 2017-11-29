package com.aillience.enginee.service;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.aillience.enginee.util.MyLog;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Happy every day.
 * Created by yfl on 2017/11/10 0010
 * explain: 做一个测试的心跳服务
 * @author yfl
 */


public class LivenHeartService extends Service {
    private String tag ="LivenHeartService";
    private ScheduledExecutorService executor;
    private int count=1;
    private Handler heartHandler= new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                MyLog.i(tag, "第 " + count + " 次跳动");
                count++;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        MyLog.i(tag,"onBind");
        return null;
    }

    @Override
    public void onCreate() {
        MyLog.i(tag,"onCreate");
        initExecutor();
        super.onCreate();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        MyLog.i(tag,"onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onTrimMemory(int level) {
        MyLog.i(tag,"onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        MyLog.i(tag,"onStartCommand");
        start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLog.i(tag,"onDestroy");
        stop();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        MyLog.i(tag,"onUnbind");
        return super.onUnbind(intent);
    }
    private void initExecutor(){
        if(executor==null){
///         executor= Executors.newScheduledThreadPool(1);
            BasicThreadFactory basicThreadFactory=new BasicThreadFactory.Builder().namingPattern("live_heart").daemon(true).build();
            executor= new ScheduledThreadPoolExecutor(1,basicThreadFactory);
        }
    }
    private void start(){
        if(executor==null){
            initExecutor();
        }
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                heartHandler.sendEmptyMessage(1);
            }
        },0L,3000L,TimeUnit.MILLISECONDS);
    }
    private void stop(){
        if(executor!=null){
            executor.shutdown();
        }
        count=1;
        executor=null;
    }
}
