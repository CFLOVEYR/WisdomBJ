package com.tofirst.study.zhbj.activity.base.content;

import android.app.Activity;
import android.view.View;

/**
 * Created by Study on 2015/12/4.
 */
public class GovAffairContentPaper extends BaseContentPaper {
    public GovAffairContentPaper(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("政务");//设置标题
        iv_menu.setVisibility(View.VISIBLE);//设置菜单不显示
        //给内容模拟添加内容
        tv.setText("政务");
        //设置能侧滑
        setSlidingMenuEnable(true);
    }
}
