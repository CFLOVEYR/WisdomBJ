package com.tofirst.study.zhbj.activity.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 取消ViewPager的侧滑事件
 */
public class NoHorizontalViewPager extends ViewPager {

    private int startX;
    private int startY;

    public NoHorizontalViewPager(Context context) {
        super(context);
    }

    public NoHorizontalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 请求父控件及其祖宗控件不要拦截
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /**
         * 分析什么情况可以让父控件拦截
         * 1.上下滑动的时候
         * 2.往右滑的时候,而且是第一个的时候,才需要父控件拦截
         * 3.往左滑动的时候,而且是这个组最后一个才需要拦截
         *
         */
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);//请求不拦截
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                if (Math.abs(endX - startX) > Math.abs(endY - startY)) {//左右滑动
                    if (endX > startX && getCurrentItem() == 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);//请求拦截
                    }
                    if (endX < startX && getCurrentItem() == getAdapter().getCount() - 1) {
                        getParent().requestDisallowInterceptTouchEvent(false);//请求拦截
                    }
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);//请求拦截
                }


                break;
            case MotionEvent.ACTION_POINTER_UP:

                break;
            default:

                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
