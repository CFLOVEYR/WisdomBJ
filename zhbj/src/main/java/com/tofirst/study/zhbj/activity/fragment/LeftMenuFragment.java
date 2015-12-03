package com.tofirst.study.zhbj.activity.fragment;

import android.view.View;

import com.tofirst.study.zhbj.R;

/**
 * 侧边栏的fragment
 */
public class LeftMenuFragment extends BaseFragment {

    @Override
    public View initViews() {
        View view = View.inflate(mActivty, R.layout.fragment_left_menu, null);
        return view;
    }
}
