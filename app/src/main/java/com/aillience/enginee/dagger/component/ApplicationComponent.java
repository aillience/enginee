package com.aillience.enginee.dagger.component;

import android.content.Context;

import com.aillience.enginee.dagger.module.ApplicationModule;
import com.aillience.enginee.dagger.scope.AppScope;
import com.aillience.enginee.dagger.scope.ContextName;

import dagger.Component;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: 作用释义
 */

@AppScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextName
    Context getApplication();
}