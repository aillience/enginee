package com.aillience.enginee.service;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;


import com.aillience.enginee.util.MLog;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Happy every day.
 * Created by yfl on 2017/11/10 0010
 * explain: 做一个测试的心跳服务
 */


public class LivenHeartService extends Service {
    private String TAG="LivenHeartService";
    private Timer timer=new Timer();
    private int count=1;
    private Handler heartHandler= new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                MLog.i(TAG, "第 " + count + " 次跳动");
                count++;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        MLog.i(TAG,"onBind");
        return null;
    }

    @Override
    public void onCreate() {
        MLog.i(TAG,"onCreate");
        if(timer==null){
            timer=new Timer();
        }
        super.onCreate();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        MLog.i(TAG,"onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onTrimMemory(int level) {
        MLog.i(TAG,"onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        MLog.i(TAG,"onStartCommand");
        start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MLog.i(TAG,"onDestroy");
        if(timer!=null){
            timer.cancel();
        }
        count=1;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        MLog.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }
    private void start(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                heartHandler.sendEmptyMessage(1);
            }
        },1000,3000);
    }
}
