package com.aillience.enginee.dagger.component;

import android.app.Activity;
import android.content.Context;

import com.aillience.enginee.dagger.module.ActivityModule;
import com.aillience.enginee.dagger.scope.ActivityScope;
import com.aillience.enginee.dagger.scope.ContextName;
import com.aillience.enginee.ui.express.ExpressActivity;
import com.aillience.enginee.ui.login.LoginActivity;

import dagger.Component;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: 作用释义
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    @ContextName("Activity")
    Context getActivityContext();

    @ContextName("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(LoginActivity loginActivity);

    void inject(ExpressActivity expressActivity);
}
