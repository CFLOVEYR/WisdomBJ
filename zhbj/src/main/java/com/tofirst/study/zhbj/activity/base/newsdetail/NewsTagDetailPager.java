package com.tofirst.study.zhbj.activity.base.newsdetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Study on 2015/12/6.
 */
public class NewsTagDetailPager extends BaseLeftMenuPaper {
    String title;
    private TextView textView;

    public NewsTagDetailPager(Activity mActivity, String title) {
        super(mActivity);
        this.title = title;
    }

    @Override
    public View initViews() {
        textView = new TextView(mActivity);
        textView.setText("新闻标签页");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText(title);
    }
}
