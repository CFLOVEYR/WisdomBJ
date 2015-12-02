package com.tofirst.study.zhbj.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.tofirst.study.zhbj.R;

/**
 * 闪屏页面
 */
public class SpalshActivity extends AppCompatActivity {
    private RelativeLayout rl_splash;
    private AnimationSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        initView();
        initAnimations();
        initListener();
    }

    /**
     * 事件
     */
    private void initListener() {
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                enterNext();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 调到下一页面的逻辑
     */
    private void enterNext() {
        //跳转到引导页面
        startActivity(new Intent(SpalshActivity.this, GuideActivity.class));
    }

    /**
     * 初始化动画
     */
    private void initAnimations() {
        set = new AnimationSet(false);
        //旋转动画 0->360
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3000);
        rotate.setFillAfter(true);//动画完成后不返回的制定
        //缩放动画  0->1的缩放
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(3000);
        scale.setFillAfter(true);//动画完成后不返回的制定
        //渐变动画(淡入淡出)  0->1
        AlphaAnimation alpha = new AlphaAnimation(0, 1.0f);
        alpha.setDuration(3000);
        alpha.setFillAfter(true);//动画完成后不返回的制定
        //添加到动画集合中
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        //开启动画
        rl_splash.startAnimation(set);
    }

    /**
     * 初始化组件
     */
    private void initView() {
        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
    }
}
