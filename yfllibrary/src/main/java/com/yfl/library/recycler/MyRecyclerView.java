package com.yfl.library.recycler;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


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

    private float dividerHeight = 2;
    private int dividerColor = Color.GRAY;
    private LinearLayoutManager mLayoutManager;
    private GridLayoutManager mGridLayoutManager;

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
        initView(context);
    }

    private void initView(Context context){
        mContext=context;
        //modify by yfl 必须设置，不能默认
        //        setLinearLayoutManager(true);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        this.setHasFixedSize(true);
        this.setNestedScrollingEnabled(false);
        //解决数据加载完成后, 没有停留在顶部的问题
        this.setFocusable(false);
    }

    /**
     * LinearLayoutManager布局
     * @param vertical //是否垂直
     */
    public void setLinearLayoutManager(final boolean vertical){
        if(mLayoutManager == null){
            mLayoutManager = new LinearLayoutManager(mContext){
                @Override
                protected int getExtraLayoutSpace(RecyclerView.State state) {
                    //预加载10
                    return 10;
                }
            };

            if(vertical){
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            }else {
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            }
        }
        layoutManager=mLayoutManager;
        this.setLayoutManager(mLayoutManager);
    }

    /**
     * GridLayoutManager布局
     * @param spanCount //行数
     * @param vertical  //是否垂直
     */
    public void setGridLayoutManager(int spanCount,final boolean vertical){
        if(spanCount==0){
            spanCount=1;
        }
        if(mGridLayoutManager == null){
            mGridLayoutManager = new GridLayoutManager(mContext,spanCount){
                @Override
                protected int getExtraLayoutSpace(RecyclerView.State state) {
                    //预加载10
                    return 10;
                }
            };
            if(vertical){
                mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            }else {
                mGridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            }
        }
        layoutManager=mGridLayoutManager;
        this.setLayoutManager(mGridLayoutManager);
    }
    public LinearLayoutManager getLayoutManager(){
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
        if(layoutManager == null){
            return;
        }
        if(vertical){
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            invalidate();
        }else {
            layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            invalidate();
        }
    }
    private float dp2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale);
    }
    /**
     * 列表滚动到指定位置
     * @param position 位置
     */
    public void scrollToPosition(int position){
        layoutManager.scrollToPosition(position);
    }

    /**
     * 平滑到指定位置
     * @param position 指定坐标
     */
    public void smoothScrollToPosition(int position){
        layoutManager.smoothScrollToPosition(this,new RecyclerView.State(),position);
    }

    //获取当前可见的屏幕上第一个位置
    public int findFirstVisibleItemPositions(){
        if(layoutManager != null){
            return layoutManager.findFirstVisibleItemPosition();
        }
        return 0;
    }

    public int findLastVisibleItemPositions(){
        if(layoutManager != null){
            return layoutManager.findLastVisibleItemPosition();
        }
        return 0;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener != null){
            onScrollListener.onScroll(t);
        }
    }

    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setScrolListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }

    private OnScrollListener onScrollListener;

    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         * @param scrollY
         *              、
         */
        public void onScroll(int scrollY);
    }
}
