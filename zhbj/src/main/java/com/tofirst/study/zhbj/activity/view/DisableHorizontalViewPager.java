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
     * 请求父控件及其祖宗控件不要拦截
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentItem() == 0) {
            //如果是最左边的内容,就请求拦截
            getParent().requestDisallowInterceptTouchEvent(false);//请求拦截
        }else {
            //如果不是最左边的内容,就请求不拦截
            getParent().requestDisallowInterceptTouchEvent(true);//请求不拦截
        }

        return super.dispatchTouchEvent(ev);
    }
}
