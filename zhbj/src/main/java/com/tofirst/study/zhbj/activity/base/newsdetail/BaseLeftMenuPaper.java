package com.tofirst.study.zhbj.activity.base.newsdetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tofirst.study.zhbj.R;
import com.tofirst.study.zhbj.activity.activity.MainActivity;

/**
 * 侧边栏控制的菜单详情页的内容的基类
 */
public abstract class BaseLeftMenuPaper {
    public Activity mActivity;
    public final View mRootView;

    public BaseLeftMenuPaper(Activity mActivity) {
        this.mActivity = mActivity;
        mRootView = initViews();
    }

    /**
     * 初始化组件
     */
    public abstract View initViews();

    /**
     * 初始化数据
     */
    public void initData() {

    }


}
