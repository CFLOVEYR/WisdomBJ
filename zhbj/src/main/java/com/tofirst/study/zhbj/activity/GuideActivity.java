package com.tofirst.study.zhbj.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tofirst.study.zhbj.R;

public class GuideActivity extends AppCompatActivity {
    private static final String TAG = "BJ";
    private ViewPager vp_guide;
    private Button btn_guide_start;
    private LinearLayout ll_points;
    private View point_red;
    //初始化图片数据
    private int[] ImageIDs = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private int pointDiatance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
        initListener();
    }


    private void initView() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        btn_guide_start = (Button) findViewById(R.id.btn_guide_start);
        ll_points = (LinearLayout) findViewById(R.id.ll_points);
        point_red = findViewById(R.id.v_point_red);
        initPoints();
        MyAdapter adapter = new MyAdapter();
        vp_guide.setAdapter(adapter);
    }

    /**
     * 初始化小球
     */
    private void initPoints() {
        //初始化灰色小球
        for (int i = 0; i < ImageIDs.length; i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.shape_points_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            if (i != 0) {
                params.leftMargin = 15;
            }
            view.setLayoutParams(params);
            ll_points.addView(view);
        }
        //初始化红色小球
        point_red.setBackgroundResource(R.drawable.shape_point_red);
        RelativeLayout.LayoutParams params_red = new RelativeLayout.LayoutParams(15, 15);
        point_red.setLayoutParams(params_red);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 获取视图树, 对layout结束事件进行监听
        ll_points.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            // 当layout执行结束后回调此方法
            @Override
            public void onGlobalLayout() {
                //取消自己的监听
                ll_points.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //获取到间距
                pointDiatance = ll_points.getChildAt(1).getLeft() - ll_points.getChildAt(0).getLeft();
                Log.d(TAG, "PointDiatance            " + pointDiatance);

            }
        });
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
//            Log.d(TAG, "position"+position+"   positionOffset"+positionOffset+"   positionOffsetPixels"+positionOffsetPixels);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) point_red.getLayoutParams();
            /**
             *   pointDiatance * positionOffset + position * pointDiatance--->>两小球的间距*移动的百分比+当前页面页码*两小球间距
             */
            params.leftMargin = (int) (pointDiatance * positionOffset + position * pointDiatance);
            //需要重新设置了
            point_red.setLayoutParams(params);
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
