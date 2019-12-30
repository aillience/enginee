package com.aillience.enginee.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Happy every day.
 * Created by yfl on 2017/11/21 0021
 * explain: 作用释义
 */

public class MyImageView extends AppCompatImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
