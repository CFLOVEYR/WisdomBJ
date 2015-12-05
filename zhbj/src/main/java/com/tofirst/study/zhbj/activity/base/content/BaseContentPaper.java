package com.tofirst.study.zhbj.activity.base.content;

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
import com.tofirst.study.zhbj.activity.utils.SlidingMenuUtils;

/**
 * 主页面上边的内容的基类
 */
public class BaseContentPaper {
    public Activity mActivity;
    public TextView tv_title;
    public ImageView iv_menu;
    public FrameLayout fl_base_content;
    public TextView tv;
    public View mRootView;

    public BaseContentPaper(Activity mActivity) {
        this.mActivity = mActivity;
        initViews();
//        initData();//防止ViewPager预加载,选中后才开始加载数据,防止逻辑误差
    }

    public void initViews() {
        mRootView = View.inflate(mActivity, R.layout.base_content, null);
        tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
        iv_menu = (ImageView) mRootView.findViewById(R.id.iv_content_title_menu);
        fl_base_content = (FrameLayout) mRootView.findViewById(R.id.fl_base_content);
        //模拟添加数据
        tv = new TextView(mActivity);
        tv.setTextColor(Color.RED);
        tv.setTextSize(18);
        tv.setGravity(Gravity.CENTER);
        fl_base_content.addView(tv);
        /**
         *  添加menu图片显示隐藏侧滑菜单
         */
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSlidingMenuToggle();
            }
        });
    }

    /**
     * 显示或者隐藏菜单栏的方法
     */
    private void setSlidingMenuToggle() {
        SlidingMenuUtils.setSlidingMenuToggle(mActivity);
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 设置是否能侧滑
     *
     * @param enabled
     */
    public void setSlidingMenuEnable(boolean enabled) {
        MainActivity MainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = MainUI.getSlidingMenu();
        if (enabled) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

    }
}
