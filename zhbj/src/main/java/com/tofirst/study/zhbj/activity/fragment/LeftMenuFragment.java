package com.tofirst.study.zhbj.activity.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tofirst.study.zhbj.R;
import com.tofirst.study.zhbj.activity.activity.MainActivity;
import com.tofirst.study.zhbj.activity.base.content.NewsContentPaper;
import com.tofirst.study.zhbj.activity.bean.NewsData;
import com.tofirst.study.zhbj.activity.utils.LogUtils;
import com.tofirst.study.zhbj.activity.utils.SlidingMenuUtils;

import java.util.ArrayList;

/**
 * 侧边栏的fragment
 */
public class LeftMenuFragment extends BaseFragment {
    private ListView lv_menu;
    private ArrayList<NewsData.NewsMenuData> datamenu;
    private MyLvAdapter adapter;
    private int mCurrentPosition;

    @Override
    public View initViews() {
        View view = View.inflate(mActivty, R.layout.fragment_left_menu, null);
        lv_menu = (ListView) view.findViewById(R.id.lv_left_menu);
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置菜单详情页的方法
                MainActivity MainUI = (MainActivity) mActivty;
                ContentFragment contentFragment = MainUI.getContentFragment();
                NewsContentPaper news = contentFragment.getNewsContentPager();
                news.setLeftMenuDetailPager(position);
                //隐藏侧滑菜单
                SlidingMenuUtils.setSlidingMenuToggle(mActivty);
                mCurrentPosition = position;
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void initData() {
        super.initData();

    }

    /**
     * 接收数据的方法
     *
     * @param data
     */
    public void setMenuData(NewsData data) {
        datamenu = data.data;
        adapter = new MyLvAdapter();
        lv_menu.setAdapter(adapter);
    }

    class MyLvAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datamenu.size();
        }

        @Override
        public String getItem(int position) {
            return datamenu.get(position).title;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivty, R.layout.left_menu_item, null);
            TextView tv_left_menu = (TextView) view.findViewById(R.id.tv_left_menu);
            tv_left_menu.setText(getItem(position));
            if (mCurrentPosition == position) {
                tv_left_menu.setEnabled(true);
            } else {
                tv_left_menu.setEnabled(false);
            }
            return view;
        }
    }
}
