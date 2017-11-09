package com.yfl.library.base.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yfl.library.base.BaseViewHolder;
import com.yfl.library.support.MultiItemTypeSupport;


/**
 * Happy every day.
 * Created by yfl on 2017/9/20 0020
 * explain: 多类型adapter，还需要实现BaseRecyclerAdapter的静态方法
 */

public abstract class MultiItemBaseAdapter<T> extends BaseRecyclerAdapter<T> {

    protected abstract MultiItemTypeSupport<T> getSupport();

    @Override
    public int getItemViewType(int position) {
        return getSupport().getViewType(position, getData().get(position));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = getSupport().getLayId(viewType);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        return new BaseViewHolder(view);
    }

}
