package com.tofirst.study.zhbj.activity.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 取消ViewPager的侧滑事件
 */
public class DisableHorizontalViewPager extends ViewPager {
    public DisableHorizontalViewPager(Context context) {
        super(context);
    }

    public DisableHorizontalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 请求父控件及其祖宗控件不要阻拦我
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
