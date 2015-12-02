package com.tofirst.study.zhbj.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.tofirst.study.zhbj.R;

public class GuideActivity extends AppCompatActivity {
    private ViewPager vp_guide;
    private Button btn_guide_start;
    //初始化图片数据
    private int[] ImageIDs = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
        initListener();
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    private void initView() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        btn_guide_start = (Button) findViewById(R.id.btn_guide_start);
        MyAdapter adapter = new MyAdapter();
        vp_guide.setAdapter(adapter);
    }

    /**
     * 监听事件
     */
    private void initListener() {
        vp_guide.setOnPageChangeListener(new MyPageListener());
    }

    /**
     * ViewPager的监听事件
     */
    class MyPageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == ImageIDs.length - 1) {
                btn_guide_start.setVisibility(View.VISIBLE);
            } else {
                btn_guide_start.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 适配器
     */
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return ImageIDs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(GuideActivity.this, R.layout.layout_guite_item, null);
            ImageView iv_guide_item = (ImageView) view.findViewById(R.id.iv_guide_item);
            iv_guide_item.setImageResource(ImageIDs[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
