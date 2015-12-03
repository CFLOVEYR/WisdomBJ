package com.tofirst.study.zhbj.activity.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.tofirst.study.zhbj.R;


/**
 * 主内容的fragment
 */
public class ContentFragment extends BaseFragment {

    @Override
    public View initViews() {
        View view = View.inflate(mActivty, R.layout.fragment_content, null);
        return view;
    }
}
