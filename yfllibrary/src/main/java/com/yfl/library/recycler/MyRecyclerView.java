package com.yfl.library.recycler;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.yfl.library.R;

/**
 * Happy every day.
 * Created by yfl on 2017/9/18 0018
 * explain: RecyclerView基类
 */
@SuppressWarnings("unused")
@SuppressLint("Recycle")
public class MyRecyclerView extends RecyclerView {

    private LinearLayoutManager layoutManager;
    private Context mContext;
    private int mRefreshFinalMoveOffset;
    private View mRefreshHeaderView;

    private View mLoadMoreFooterView;

    public MyRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initRefresh(context,attrs,defStyle);
        initView(context);
    }

    private void initView(Context context){
        mContext=context;
        setLinearLayoutManager(true);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        this.setHasFixedSize(true);
    }
    //刷新的初始化
    private void initRefresh(Context context, @Nullable AttributeSet attrs, int defStyle){
       final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyRecyclerView, defStyle, 0);
        @LayoutRes int refreshHeaderLayoutRes = -1;
        @LayoutRes int loadMoreFooterLayoutRes = -1;
        int refreshFinalMoveOffset = -1;
        boolean refreshEnabled;
        boolean loadMoreEnabled;
        try {
            //得到设定值或者默认值
            refreshEnabled = a.getBoolean(R.styleable.MyRecyclerView_refreshEnabled, false);
            loadMoreEnabled = a.getBoolean(R.styleable.MyRecyclerView_loadMoreEnabled, false);
            refreshHeaderLayoutRes = a.getResourceId(R.styleable.MyRecyclerView_refreshHeaderLayout, -1);
            loadMoreFooterLayoutRes = a.getResourceId(R.styleable.MyRecyclerView_loadMoreFooterLayout, -1);
            refreshFinalMoveOffset = a.getDimensionPixelOffset(R.styleable.MyRecyclerView_refreshFinalMoveOffset, -1);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (mRefreshHeaderView != null) {
            if (mRefreshHeaderView.getMeasuredHeight() > mRefreshFinalMoveOffset) {
                mRefreshFinalMoveOffset = 0;
            }
        }
    }
    /**
     * LinearLayoutManager布局
     * @param vertical //是否垂直
     */
    private void setLinearLayoutManager(boolean vertical){
        LinearLayoutManager  linearLayoutManager=new LinearLayoutManager(mContext);
        layoutManager=linearLayoutManager;
        if(vertical){
            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        }else {
            linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        }
        this.setLayoutManager(linearLayoutManager);
    }

    /**
     * GridLayoutManager布局
     * @param spanCount //行数
     * @param vertical  //是否垂直
     */
    private void setGridLayoutManager(int spanCount,boolean vertical){
        if(spanCount==0){
            spanCount=1;
        }
        GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,spanCount);

        layoutManager=gridLayoutManager;
        if(vertical){
            gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        }else {
            gridLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        }
        this.setLayoutManager(gridLayoutManager);
    }
    public LayoutManager getLayoutManager(){
        return layoutManager;
    }
    /**
     * 设置LayoutManager
     * @param isDefault  默认为sLinearLayoutManager
     * @param vertical   是否垂直
     * @param spanCount  行数
     */
    public void setLayoutManager(boolean isDefault,boolean vertical,int spanCount){
        if(isDefault){
            setLinearLayoutManager(vertical);
        }else {
            setGridLayoutManager(spanCount,vertical);
        }
    }
    //设置Orientation布局，默认为垂直
    public void setOrientation(boolean vertical){
        if(vertical){
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
        }else {
            layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        }
    }

    /**
     * 列表滚动到指定位置
     * @param position 位置
     */
    public void scrollToPosition(int position){
        layoutManager.scrollToPosition(position);
    }
}
