package com.yfl.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yfl.library.R;

/**
 * Happy every day.
 * Created by yfl on 2017/11/21 0021
 * explain: 自定义一个随机验证码,随机出现字符，以及干扰项，可能是横线可能是不规则字体噪点甚至动画等；
 *
 */

public class CustomCodeView extends View{
    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;
    public CustomCodeView(Context context) {
        super(context);
    }

    public CustomCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public CustomCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCodeView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            if (attr == R.styleable.CustomCodeView_titleText) {
                mTitleText = a.getString(attr);
            } else if (attr == R.styleable.CustomCodeView_titleTextColor) {// 默认颜色设置为黑色
                mTitleTextColor = a.getColor(attr, Color.BLACK);
            } else if (attr == R.styleable.CustomCodeView_titleTextSize) {// 默认设置为16sp，TypeValue也可以把sp转化为px
                mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            }
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO 设置背景颜色等属性
    }
}
