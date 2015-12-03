package com.tofirst.study.zhbj.activity.fragment;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tofirst.study.zhbj.R;
import com.tofirst.study.zhbj.activity.view.NoScorllViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * 主内容的fragment
 */
public class ContentFragment extends BaseFragment {
    private NoScorllViewPager vp_content;
    private List<TextView> iv_lists;

    @Override
    public View initViews() {
        View view = View.inflate(mActivty, R.layout.fragment_content, null);
        vp_content = (NoScorllViewPager) view.findViewById(R.id.vp_content);
        return view;
    }

    @Override
    public void initData() {
        initViewPager();

    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        iv_lists = new ArrayList<>();
        //模拟三个图片
        for (int i = 0; i < 5; i++) {
            TextView tv = new TextView(mActivty);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(20);
            tv.setTextColor(Color.RED);
            iv_lists.add(tv);
        }
        vp_content.setAdapter(new MyAdapter());
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return iv_lists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = iv_lists.get(position);
            tv.setText("首页   " + position );
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
