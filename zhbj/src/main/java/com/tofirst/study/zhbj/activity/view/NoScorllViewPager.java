package com.tofirst.study.zhbj.activity.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 取消ViewPager的侧滑事件
 */
public class NoScorllViewPager extends ViewPager {
    public NoScorllViewPager(Context context) {
        super(context);
    }

    public NoScorllViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 父控件自己不处理,分配给子控件处理
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    /**
     * 父控件不处理触摸事件
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
