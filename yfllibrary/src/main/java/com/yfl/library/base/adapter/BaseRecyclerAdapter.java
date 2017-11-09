package com.yfl.library.base.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yfl.library.base.BaseViewHolder;

import java.util.List;

/**
 * Happy every day.
 * Created by yfl on 2017/9/18 0018
 * explain: RecyclerAdapter基类
 */
@SuppressWarnings("unused")
@SuppressLint("RecyclerView")
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    protected abstract Context getContext();
    protected abstract List<T> getData();
    protected abstract int getLayoutId();
    protected abstract void bindViewHolder(BaseViewHolder holder, T item, int position);
    private viewClick clickEvent;
    public interface viewClick<T>{
        void onItemClick(View view, T item, int position);
        void onItemLongClick(View view, T item, int position);
    }
    public void setViewClick(viewClick action){
        this.clickEvent=action;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(),parent ,false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder,  final int position) {
        //绑定BaseViewHolder的数据显示，监听等
        bindViewHolder(holder,getItem(position),position);
        if(clickEvent!=null){
            final View finalConvertView = holder.getConvertView();
            finalConvertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickEvent.onItemClick(v,getItem(position),position);
                }
            });
            finalConvertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickEvent.onItemLongClick(v,getItem(position),position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (getData()!=null)?getData().size():0;
    }
    private T getItem(int position){
        return (getData()==null) ?null:getData().get(position);
    }
    //插入数据
    public void addData(int position,T item){
        getData().add(position,item);
        notifyItemInserted(position);
    }
    //删除数据
    public void deleteData(int position){
        getData().remove(position);
        notifyItemRemoved(position);
    }
}
