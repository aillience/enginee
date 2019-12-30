package com.yfl.library.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yfl.library.base.BaseViewHolder;

import java.util.List;

/**
 * ===============================
 * 千万不要出现 B U G ，出现就 G G
 * THE BEST CODE IS NO CODE
 * ===============================
 *
 * @author:yfl
 * @date: 2019-12-27
 * @description: 一个在 recyclerview -27上使用的适配器
 * @lastUpdateTime 2019-12-27
 * #更新内容
 * ===============================
 **/

public abstract class BaseDiffAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{
    /**
     * 上下文
     * @return Context
     */
    protected abstract Context getContext();

    /**
     * 数据对象
     * @return List
     */
    protected abstract List<T> getData();

    /**
     * 布局文件id
     * @return ID
     */
    protected abstract int getLayoutId();

    /**
     * 布局绑定
     * @param holder BaseViewHolder
     * @param item 对象
     * @param position 位置
     */
    protected abstract void bindViewHolder(BaseViewHolder holder, T item, int position);

    protected AsyncListDiffer<T> listDiffer;
    private itemClickEvent<T> clickEvent;
    public interface itemClickEvent<T>{
        void onItemClick(View view, T item, int position);
        void onItemLongClick(View view, T item, int position);
    }
    public void setViewClick(itemClickEvent<T> action){
        this.clickEvent = action;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(),parent ,false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, int position) {
        //绑定BaseViewHolder的数据显示，监听等
        bindViewHolder(holder,getItem(position),position);
        if(clickEvent!=null){
            final View finalConvertView = holder.getConvertView();
            finalConvertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickEvent.onItemClick(v,getItem(holder.getAdapterPosition()),holder.getAdapterPosition());
                }
            });
            finalConvertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickEvent.onItemLongClick(v,getItem(holder.getAdapterPosition()),holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
//        Log.d("adapter",String.format(Locale.CHINA,"listDiffer 是不是null：%b",(listDiffer==null)));
        return (listDiffer==null)? getData().size():listDiffer.getCurrentList().size();
    }

    private T getItem(int position){
        return (listDiffer==null) ?getData().get(position):listDiffer.getCurrentList().get(position);
    }

    /**
     * 数据提交,将比较放在了子线程中 不再中主线程中比较减少刷新造成的卡顿
     */
    public void submitList() {
        if(listDiffer == null){
            //全局刷新
            notifyDataSetChanged();
        }else {
            //局部刷新
            listDiffer.submitList(getData());
        }
    }

}
