package com.aillience.enginee.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.aillience.enginee.dagger.component.ApplicationComponent;
import com.aillience.enginee.dagger.component.DaggerApplicationComponent;
import com.aillience.enginee.dagger.module.ApplicationModule;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 应用层
 * 简单描述：此前自我有使用并撰写更改过一些框架，但是，无疑他人的框架始终是他人的。而且那些完整框架
 * 基本带有太多作者的个人理解以及比较不易懂的在里面。不太适合新人使用学习，故作为一萌新，将自己的学习
 * 经验，用代码写一个不算完整的框架，算是给自己一个学习总结吧。
 * 此框架旨在整理出一个规范的代码结构。给使用者养成一个良好的代码习惯。
 * 同时，里面只是简单的对基类等进行了简约封装。尽量减少了个人的代码风格。给予新人引导，
 * 又不想新人被此模式给禁锢。充分给予大家重铸发挥的空间。
 */
@SuppressLint("StaticFieldLeak")
public class MyApp extends Application {
    private ApplicationComponent mApplicationComponent;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        initApplicationComponent();
    }
    public static Context getAppContext(){
        return mContext;
    }
    public static MyActivityManager getActManager(){
        return MyActivityManager.newInstance();
    }
    // Fixme
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
