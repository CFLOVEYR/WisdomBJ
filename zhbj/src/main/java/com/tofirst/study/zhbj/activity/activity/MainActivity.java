package com.tofirst.study.zhbj.activity.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tofirst.study.zhbj.R;
import com.tofirst.study.zhbj.activity.fragment.ContentFragment;
import com.tofirst.study.zhbj.activity.fragment.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity {

    public static final String LEFTFRAGMENT = "leftfragment";
    public static final String CONTENTFRAGMENT = "contentfragment";
    public FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initSlidingMenu();
        initView();
    }

    /**
     * 初始化Fragment
     */
    private void initView() {

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        LeftMenuFragment leftMenuFragment = new LeftMenuFragment();
        ContentFragment contentFragment = new ContentFragment();
        //给主页面赋值内容
        transaction.replace(R.id.fl_content, contentFragment, CONTENTFRAGMENT);
        //给左边侧滑菜单赋值内容
        transaction.replace(R.id.fl_menu, leftMenuFragment, LEFTFRAGMENT);
        transaction.commit();
    }

    /**
     * 侧边栏的初始化
     */
    private void initSlidingMenu() {

        //侧滑菜单的引用
        setBehindContentView(R.layout.left_menu);// 设置侧边栏布局

        SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸

//        slidingMenu.setSecondaryMenu(R.layout.right_menu);// 设置右侧边栏
        slidingMenu.setMode(SlidingMenu.LEFT);// 设置展现模式

        slidingMenu.setBehindOffset(150);// 设置预留屏幕的宽度
    }

    // 获取侧边栏fragment
    public LeftMenuFragment getLeftMenuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        LeftMenuFragment fragment = (LeftMenuFragment) fm
                .findFragmentByTag(LEFTFRAGMENT);

        return fragment;
    }

    // 获取主页面fragment
    public ContentFragment getContentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ContentFragment fragment = (ContentFragment) fm
                .findFragmentByTag(CONTENTFRAGMENT);

        return fragment;
    }
}
