package com.tofirst.study.zhbj.activity.base.content;

import android.app.Activity;
import android.view.View;

/**
 * Created by Study on 2015/12/4.
 */
public class NewsContentPaper extends BaseContentPaper {
    public NewsContentPaper(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("新闻中心");//设置标题
        iv_menu.setVisibility(View.VISIBLE);//设置菜单不显示
        //给内容模拟添加内容
        tv.setText("新闻中心");
        //设置能侧滑
        setSlidingMenuEnable(true);
    }
}
