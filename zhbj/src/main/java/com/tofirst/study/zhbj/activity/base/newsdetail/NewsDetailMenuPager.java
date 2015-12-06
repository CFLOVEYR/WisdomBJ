package com.tofirst.study.zhbj.activity.base.newsdetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tofirst.study.zhbj.R;
import com.tofirst.study.zhbj.activity.activity.MainActivity;
import com.tofirst.study.zhbj.activity.bean.NewsData;
import com.tofirst.study.zhbj.activity.bean.NewsDetailData;
import com.tofirst.study.zhbj.activity.global.Global;
import com.tofirst.study.zhbj.activity.utils.LogUtils;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单详情页--新闻
 */
public class NewsDetailMenuPager extends BaseLeftMenuPaper {

    public ViewPager vp_newsdetail;
    public List<NewsTagDetailPager> detailPagerList;
    ArrayList<NewsData.NewsMenuData> datamenu;
    private ArrayList<NewsData.NewsTagData> children;
    private TabPageIndicator indicator;
    private int mCurrentPage;
    private MyAdapter adapter;


    private ArrayList<NewsDetailData.NewsTagData_TopNews> topnews;

    public NewsDetailMenuPager(Activity mActivity, ArrayList<NewsData.NewsMenuData> datamenu) {
        super(mActivity);
        this.datamenu = datamenu;
        children = datamenu.get(0).children;
    }

    @Override
    public View initViews() {
        //添加数据
        View view = View.inflate(mActivity, R.layout.left_menu_newsdetail, null);
        vp_newsdetail = (ViewPager) view.findViewById(R.id.vp_newsdetail);

        ImageButton iv_newstail_next = (ImageButton) view.findViewById(R.id.iv_newstail_next);
        iv_newstail_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp_newsdetail.setCurrentItem(++mCurrentPage);
                adapter.notifyDataSetChanged();
            }
        });
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator_newsdetail);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                MainActivity MainUI = (MainActivity) mActivity;
                SlidingMenu slidingMenu = MainUI.getSlidingMenu();
                if (position == 0) {
                    //如果是第一个就可能侧滑,否则不能侧滑
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                } else {
                    //否则不能侧滑
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        detailPagerList = new ArrayList<NewsTagDetailPager>();
        for (int i = 0; i < children.size(); i++) {
            detailPagerList.add(new NewsTagDetailPager(mActivity, children.get(i)));
        }
        adapter = new MyAdapter();
        vp_newsdetail.setAdapter(adapter);
        //必须在设置适配器后才能调用设置引导器
        indicator.setViewPager(vp_newsdetail);

    }


    /**
     * 适配器
     */
    class MyAdapter extends PagerAdapter {
        @Override
        public CharSequence getPageTitle(int position) {
            return children.get(position).title;
        }

        @Override
        public int getCount() {
            return detailPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(detailPagerList.get(position).mRootView);
            detailPagerList.get(position).initData();
            return detailPagerList.get(position).mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
