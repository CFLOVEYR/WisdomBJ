package com.tofirst.study.zhbj.activity.base.content;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tofirst.study.zhbj.activity.activity.MainActivity;
import com.tofirst.study.zhbj.activity.base.newsdetail.BaseLeftMenuPaper;
import com.tofirst.study.zhbj.activity.base.newsdetail.InteractiveDetailMenuPager;
import com.tofirst.study.zhbj.activity.base.newsdetail.NewsDetailMenuPager;
import com.tofirst.study.zhbj.activity.base.newsdetail.PhotosDetailMenuPager;
import com.tofirst.study.zhbj.activity.base.newsdetail.TopicDetailMenuPager;
import com.tofirst.study.zhbj.activity.bean.NewsData;
import com.tofirst.study.zhbj.activity.fragment.LeftMenuFragment;
import com.tofirst.study.zhbj.activity.global.Global;
import com.tofirst.study.zhbj.activity.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面-新闻中心
 */
public class NewsContentPaper extends BaseContentPaper {

    public ArrayList<NewsData.NewsMenuData> datamenu;
    public NewsData data;
    private List<BaseLeftMenuPaper> list_left_menus;

    public NewsContentPaper(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initData() {
        super.initData();
       //设置能侧滑
        setSlidingMenuEnable(true);
        getDataFromServer();
    }

    /**
     * 从服务器获得信息
     */
    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Global.DATAURL, new RequestCallBack<String>() {
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
     * 解析得到的数据放到类中
     *
     * @param result
     */
    private void parseResult(String result) {
        //解析数据
        Gson gson = new Gson();
        data = gson.fromJson(result, NewsData.class);
        datamenu = data.data;
        //给侧滑菜单赋值内容
        MainActivity MainUI = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = ((MainActivity) mActivity).getLeftMenuFragment();
        leftMenuFragment.setMenuData(data);
        //给侧滑菜单详情页集合添加内容
        list_left_menus = new ArrayList<BaseLeftMenuPaper>();
        list_left_menus.add(new NewsDetailMenuPager(mActivity,datamenu));//新闻
        list_left_menus.add(new TopicDetailMenuPager(mActivity));//专题
        list_left_menus.add(new PhotosDetailMenuPager(mActivity));//组图
        list_left_menus.add(new InteractiveDetailMenuPager(mActivity));//互动
        //需要加载完数据,点击新闻中心,则显示新闻菜单详情页的第一页
        setLeftMenuDetailPager(0);

    }

    /**
     * 给菜单详情页赋值的方法
     *
     * @param position
     */
    public void setLeftMenuDetailPager(int position) {
        BaseLeftMenuPaper baseLeftMenuPaper = list_left_menus.get(position);
        fl_base_content.removeAllViews();//赋值的时候清空以前的组件
        fl_base_content.addView(baseLeftMenuPaper.mRootView);
        //主页面的标题,通过服务器得到的信息
        tv_title.setText(datamenu.get(position).title);
        //初始化数据
        baseLeftMenuPaper.initData();
    }
}
