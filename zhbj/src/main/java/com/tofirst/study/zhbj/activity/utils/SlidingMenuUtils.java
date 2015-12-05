package com.tofirst.study.zhbj.activity.utils;

import android.content.Context;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tofirst.study.zhbj.activity.activity.MainActivity;

/**
 * Created by Study on 2015/12/6.
 */
public class SlidingMenuUtils {
    /**
     * 显示或者隐藏菜单栏的方法
     */
    public static void setSlidingMenuToggle(Context context) {
        MainActivity MainUI = (MainActivity) context;
        SlidingMenu slidingMenu = MainUI.getSlidingMenu();
        slidingMenu.toggle();//切换菜单显示隐藏的方法,调用这个方法后:显示->隐藏  隐藏->显示
    }
}
