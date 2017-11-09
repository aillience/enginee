package com.yfl.library.support;

/**
 * Happy every day.
 * Created by yfl on 2017/9/20 0020
 * explain: 多类型item接口支持 ，由hy大神提供思路
 */

public interface MultiItemTypeSupport<T> {
    int getLayId(int viewType);
    int getViewType(int position, T t);
}
