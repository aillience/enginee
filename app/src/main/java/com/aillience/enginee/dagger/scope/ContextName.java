package com.aillience.enginee.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: 作用释义
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextName {
    String value() default "Application";
}
