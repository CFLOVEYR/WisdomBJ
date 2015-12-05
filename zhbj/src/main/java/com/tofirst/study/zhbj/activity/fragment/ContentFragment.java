package com.tofirst.study.zhbj.activity.fragment;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tofirst.study.zhbj.R;
import com.tofirst.study.zhbj.activity.base.content.BaseContentPaper;
import com.tofirst.study.zhbj.activity.base.content.GovAffairContentPaper;
import com.tofirst.study.zhbj.activity.base.content.HomeContentPaper;
import com.tofirst.study.zhbj.activity.base.content.NewsContentPaper;
import com.tofirst.study.zhbj.activity.base.content.SettingContentPaper;
import com.tofirst.study.zhbj.activity.base.content.SmartContentPaper;
import com.tofirst.study.zhbj.activity.view.NoScorllViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * 主内容的fragment
 */
public class ContentFragment extends BaseFragment {
    public NoScorllViewPager vp_content;
    public List<TextView> iv_lists;
    public RadioGroup rp_content;
    public List<BaseContentPaper> list_contentPapers;
    public MyAdapter adapter;

    @Override
    public View initViews() {
        View view = View.inflate(mActivty, R.layout.fragment_content, null);
        vp_content = (NoScorllViewPager) view.findViewById(R.id.vp_content);
        rp_content = (RadioGroup) view.findViewById(R.id.rp_content);
        return view;
    }

    @Override
    public void initData() {
        initViewPager();
        initRadioGroup();
    }

    /**
     * RadioGroup的事件
     */
    private void initRadioGroup() {
        /**
         * 默认选择第一个
         */
        rp_content.check(R.id.rb_bottag_home);

        /**
         *  如果被选中的话就切换标签
         */
        rp_content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_bottag_home:
//                        vp_content.setCurrentItem(0);
                        vp_content.setCurrentItem(0, false);//去掉切换的平滑移动效果
                        break;
                    case R.id.rb_bottag_news:
                        vp_content.setCurrentItem(1, false);
                        break;
                    case R.id.rb_bottag_smart:
                        vp_content.setCurrentItem(2, false);
                        break;
                    case R.id.rb_bottag_gov:
                        vp_content.setCurrentItem(3, false);
                        break;
                    case R.id.rb_bottag_setting:
                        vp_content.setCurrentItem(4, false);
                        break;
                    default:

                        break;
                }
            }
        });
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        //主页面内容数据的的准备
        list_contentPapers = new ArrayList<BaseContentPaper>();
        list_contentPapers.add(new HomeContentPaper(mActivty));
        list_contentPapers.add(new NewsContentPaper(mActivty));
        list_contentPapers.add(new SmartContentPaper(mActivty));
        list_contentPapers.add(new GovAffairContentPaper(mActivty));
        list_contentPapers.add(new SettingContentPaper(mActivty));
        adapter = new MyAdapter();
        vp_content.setAdapter(adapter);
        /**
         * 开始就让第一个页面加载数据,防止第一页面能侧滑
         */
        list_contentPapers.get(0).initData();
        /**
         *   监听ViewPaper的事件,避免预加载
         */
        vp_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                list_contentPapers.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public NewsContentPaper getNewsContentPager() {
        return (NewsContentPaper) list_contentPapers.get(1);
    }


    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list_contentPapers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseContentPaper baseContentPaper = list_contentPapers.get(position);
            container.addView(baseContentPaper.mRootView);
            return baseContentPaper.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
