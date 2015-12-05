package com.tofirst.study.zhbj.activity.base.newsdetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 *
 *  菜单详情页--专题
 *
 */
public class TopicDetailMenuPager extends BaseLeftMenuPaper {
    public TopicDetailMenuPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initViews() {
        //添加数据
        TextView tvdetail = new TextView(mActivity);
        tvdetail.setText("菜单详情页-专题");
        tvdetail.setTextSize(20);
        tvdetail.setTextColor(Color.RED);
        tvdetail.setGravity(Gravity.CENTER);
        return tvdetail;
    }
}
