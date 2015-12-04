package com.tofirst.study.zhbj.activity.base.content;

import android.app.Activity;
import android.view.View;

/**
 * Created by Study on 2015/12/4.
 */
public class HomeContentPaper extends BaseContentPaper {
    public HomeContentPaper(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("主页");//设置标题
        iv_menu.setVisibility(View.INVISIBLE);//设置菜单不显示
        //给内容模拟添加内容
        tv.setText("主页");
        //设置不能侧滑
        setSlidingMenuEnable(false);
    }
}
