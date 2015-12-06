package com.tofirst.study.zhbj.activity.base.newsdetail;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tofirst.study.zhbj.R;
import com.tofirst.study.zhbj.activity.bean.NewsData;
import com.tofirst.study.zhbj.activity.bean.NewsDetailData;
import com.tofirst.study.zhbj.activity.global.Global;
import com.tofirst.study.zhbj.activity.utils.LogUtils;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;

/**
 * 页签详情页的具体实现
 */
public class NewsTagDetailPager extends BaseLeftMenuPaper {
    private NewsData.NewsTagData newsTagData;
    private ViewPager vp_newsdetail_tag;
    private MyAdapater adapater;
    private NewsDetailData detailData;
    private String mURL;//访问数据的地址
    private ArrayList<NewsDetailData.NewsTagData_TopNews> topnews;//头条新闻的内容
    private TextView tv_tagdetail;//头条新闻的标题
    private PageIndicator mIndicator;
    private CirclePageIndicator indicator;//小圆点指示器
    private ListView lv_newsdetail;
    private ArrayList<NewsDetailData.NewsTagData_News> news;

    public NewsTagDetailPager(Activity mActivity, NewsData.NewsTagData newsTagData) {
        super(mActivity);
        this.newsTagData = newsTagData;
        mURL = Global.URLBEFORE + newsTagData.url;
    }

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.news_tagdetail_item, null);
        View view1 = View.inflate(mActivity, R.layout.list_header_news_item, null);
        lv_newsdetail = (ListView) view.findViewById(R.id.lv_newsdetail);
        vp_newsdetail_tag = (ViewPager) view1.findViewById(R.id.vp_newsdetail_tag);
        tv_tagdetail = (TextView) view1.findViewById(R.id.tv_tagdetail_title);
        indicator = (CirclePageIndicator) view1.findViewById(R.id.indicator_circle);
        lv_newsdetail.addHeaderView(view1);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataDetaFromServer();
    }

    /**
     * 从服务器获得内容的方法
     */
    private void getDataDetaFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, mURL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                parseResult(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, "解析失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    /**
     * 解析Json数据的方法
     *
     * @param result
     */
    private void parseResult(String result) {
        Gson gson = new Gson();
        detailData = gson.fromJson(result, NewsDetailData.class);
        topnews = detailData.data.topnews;
        news = detailData.data.news;
        //判断是否有输出,防止服务器有问题,客户端会崩溃
        if (topnews != null) {
            adapater = new MyAdapater();
            vp_newsdetail_tag.setAdapter(adapater);
            /**
             *  设置图片的内容和小圆点
             */
            setTitleAndCircle();
            if (news != null) {
                lv_newsdetail.setAdapter(new MyLvAdapter());
            }
        }
    }

    /**
     * 设置图片和原点指示器的方法
     */
    private void setTitleAndCircle() {
        //设置小点点的逻辑
        mIndicator = indicator;
        indicator.setViewPager(vp_newsdetail_tag);
        indicator.setSnap(true);
        //默认选择第一个页,而不是默认聪明的选择最后一个
        indicator.onPageSelected(0);
        //第一次加载的时候就有数据了,而不是默认的数据
        tv_tagdetail.setText(topnews.get(0).title);
        /**
         * 当indicator和viewpaper遇到一起的时候,设置监听都需要对indicator设置
         */
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = topnews.get(position).title;
                tv_tagdetail.setText(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 适配器
     */
    class MyAdapater extends PagerAdapter {

        private final BitmapUtils bitutils;

        public MyAdapater() {
            bitutils = new BitmapUtils(mActivity);
            //设置默认加载图片
            bitutils.configDefaultLoadingImage(R.mipmap.topnews_item_default);
        }

        @Override
        public int getCount() {
            return topnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//设置填充图片
            bitutils.display(imageView, topnews.get(position).topimage);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * ListView的适配器
     */
    class MyLvAdapter extends BaseAdapter {

        private BitmapUtils bitutils;
        ViewHolder holder;

        public MyLvAdapter() {
            bitutils = new BitmapUtils(mActivity);
        }

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return news.get(position).title;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mActivity, R.layout.list_news_item, null);
                holder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_title.setText(news.get(position).title);
            holder.tv_date.setText(news.get(position).pubdate);
//            LogUtils.i("Test",news.get(position).listimage);
            bitutils.display(holder.iv_pic, news.get(position).listimage);
            return convertView;
        }
    }

    class ViewHolder {
        TextView tv_title;
        TextView tv_date;
        ImageView iv_pic;
    }
}
