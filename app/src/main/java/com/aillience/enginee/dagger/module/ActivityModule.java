package com.aillience.enginee.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.aillience.enginee.dagger.scope.ActivityScope;
import com.aillience.enginee.dagger.scope.ContextName;

import dagger.Module;
import dagger.Provides;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: 作用释义
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    @ContextName("Activity")
    public Context ProvideActivityContext() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    public Activity ProvideActivity() {
        return mActivity;
    }
}
