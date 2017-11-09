/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.aillience.enginee.dagger.component;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.aillience.enginee.dagger.module.FragmentModule;
import com.aillience.enginee.dagger.scope.ContextName;
import com.aillience.enginee.dagger.scope.FragmentScope;

import dagger.Component;


/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: 作用释义
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextName("Activity")
    Context getActivityContext();

    @ContextName("Application")
    Context getApplicationContext();

    Activity getActivity();
    Fragment getFragment();

    void inject(Fragment fragment);
}
