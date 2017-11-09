package com.yfl.library.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yfl.library.R;


/**
 * Created by hyz on 2017/3/12.
 * Happy every day.
 * tidy by yfl on 2017/9/14 0014
 * explain: 一个viewHolder基类，由hyz编写；yfl整理。
 */

@SuppressWarnings({"JavaDoc","unused","unchecked"})
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews;
//    private int mPosition;
    private View mConvertView;
    private Context mContext;

    public BaseViewHolder(View convertView) {
        super(convertView);
        this.mConvertView=convertView;
        this.mViews=new SparseArray<>();
    }

    //获取布局view
    public View getConvertView() {
        return mConvertView;
    }
    /**
     *   获取Context
     *   默认为mContext，没有取mConvertView.getContext()
     */
    private Context getContext(){
        if(mContext==null){
            mContext= mConvertView.getContext();
        }
        return mContext;
    }
    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return view
     */
    private <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    /**
     *  为TextView设置字符串
     * @param viewId
     * @param text
     * @return
     */
    public TextView setText(int viewId, String text){
        TextView view = getView(viewId);
        view.setText(text);
        return view;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ImageView setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return view;
    }

    /**
     * 控件设置背景图片以及透明度
     * @param viewId
     * @param drawableId
     * @return
     */
    public View setBackgroundResource(int viewId,int drawableId){
        return setBackgroundResource(viewId,drawableId,0);
    }

    /**
     * 控件设置背景图片以及透明度
     * @param viewId
     * @param drawableId
     * @param alpha
     * @return
     */
    private View setBackgroundResource(int viewId, int drawableId, int alpha){
        View v=getView(viewId);
        v.setBackgroundResource(drawableId);
        if(alpha!=0)
        v.getBackground().setAlpha(alpha);
        return v;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     */
    public void setImageByUrlIcon(int viewId, String url) {
        Glide.with(getContext()).load(url)//
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存修改过的图片
                .override(120, 120)
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .placeholder(R.drawable.img_error)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存修改过的图片
                .error(R.drawable.img_error)
                .into((ImageView) getView(viewId));
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     */
    public void setImageByUrl(int viewId, String url) {
        Glide.with(getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存修改过的图片
                .override(250, 250)
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .placeholder(R.drawable.img_error_big)
                .error(R.drawable.img_error_big)
                .into((ImageView) getView(viewId));
    }
}
