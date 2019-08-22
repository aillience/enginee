package com.yfl.library.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.yfl.library.R;
import com.yfl.library.util.images.MySimpleTarget;


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
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.img_error)	//加载成功之前占位图 icon_default_load_place
                .skipMemoryCache(false)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(new ObjectKey(url))
                .fallback(R.drawable.img_error)
                .error(R.drawable.img_error);	//加载错误之后的错误图
        Glide.with(getContext()).load(url).apply(options)
                .into((ImageView) getView(viewId));
    }
    /**
     * 为ImageView设置图片,设置标签的那种，防止列表复用时，图片错位
     *
     * @param viewId
     * @param url
     */
    public void setImageByUrl(int viewId, String url) {
        ImageView imageView = getView(viewId);
        //图片错误问题
        imageView.setTag(R.id.image_url,(url == null) ?"":url);
        Object tag = imageView.getTag(R.id.image_url);
        if(tag == null || "".equals(tag)){
            imageView.setImageResource(R.drawable.img_error);
            return;
        }
        if(url != tag){
            //如果tag不相等，代表view对象不同，不加载
            return;
        }

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.img_error)	//加载成功之前占位图 icon_default_load_place
                .skipMemoryCache(false)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(new ObjectKey(url))
                .fallback(R.drawable.img_error)
                .error(R.drawable.img_error);	//加载错误之后的错误图
        Glide.with(getContext()).load(url)
                .apply(options)
                .into(new MySimpleTarget(tag,imageView));
    }
}
