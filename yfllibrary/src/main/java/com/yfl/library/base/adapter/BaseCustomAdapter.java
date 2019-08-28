package com.yfl.library.base.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yfl.library.base.BaseViewHolder;

import java.util.List;

/**
 * Happy every day.
 * Created by yfl on 2017/9/14 0014
 * explain: adapter自定义抽象类
 */

public abstract class BaseCustomAdapter<T> extends BaseAdapter{


    //获取Context
    protected abstract Context getContext();
    //放置数据
    protected abstract List<T> getData();
    //放置布局id
    protected abstract int getLayoutId();
    //数据布局显示
    protected abstract void convert(BaseViewHolder holder, T item);

    public interface viewClickListener<T>{
        void onItemClick(View view, T item, int position);
        void onItemLongClick(View view, T item, int position);
    }

    private viewClickListener<T> viewClick=null;

    public void setViewClick(viewClickListener<T> iViewClick) {
        this.viewClick = iViewClick;
    }

    @Override
    public int getCount() {
        return (getData()==null)? 0:getData().size();
    }

    @Override
    public T getItem(int position) {
        return (getData()==null) ?null:getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return (getData()==null)? 0:position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(getLayoutId(), parent,
                    false);
            viewHolder=new BaseViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(BaseViewHolder) convertView.getTag();
        }
        convert(viewHolder,getItem(position));
        if(viewClick!=null){
            final View finalConvertView = convertView;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewClick.onItemClick(finalConvertView,getItem(position),position);
                }
            });
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    viewClick.onItemLongClick(finalConvertView,getItem(position),position);
                    return false;
                }
            });
        }
        return convertView;
    }

    /**
     *单条数据更新
     * @param position 要更新的位置
     */
    private void updateSingle(ListView listView, int position) {
        /*第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /*最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /*在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /*获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            getView(position,view,listView);
        }
    }
}
