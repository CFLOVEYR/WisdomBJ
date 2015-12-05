package com.tofirst.study.zhbj.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment的基类
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mActivty = getActivity();
        super.onCreate(savedInstanceState);
    }

    //回执的过程
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initViews();
    }

    //fragment在activty中的组件绘制完成后调用
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    //子类必须实现
    public abstract View initViews();

    /**
     * 初始化数据
     */
    public void initData() {

    }
}
