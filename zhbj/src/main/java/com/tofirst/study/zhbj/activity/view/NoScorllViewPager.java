package com.tofirst.study.zhbj.activity.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *  取消ViewPager的侧滑事件
 */
public class NoScorllViewPager extends ViewPager {
    public NoScorllViewPager(Context context) {
        super(context);
    }

    public NoScorllViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
