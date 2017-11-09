package com.aillience.enginee.dagger.module;

import android.content.Context;

import com.aillience.enginee.app.MyApp;
import com.aillience.enginee.dagger.scope.AppScope;
import com.aillience.enginee.dagger.scope.ContextName;

import dagger.Module;
import dagger.Provides;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: 作用释义
 */


@Module
public class ApplicationModule {
    private MyApp mApplication;

    public ApplicationModule(MyApp application) {
        mApplication = application;
    }

    @Provides
    @AppScope
    @ContextName
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

}