package com.tofirst.study.zhbj.activity.bean;

import java.util.ArrayList;

/**
 * Created by Study on 2015/12/6.
 */
public class NewsDetailData {
    public int retcode;

    public NewsTagData data;

    public class NewsTagData {
        public String more;
        public String title;
        public ArrayList<NewsTagData_News> news;
        public ArrayList<NewsTagData_TopNews> topnews;

        @Override
        public String toString() {
            return "NewsTagData{" +
                    "title='" + title + '\'' +
                    ", news=" + news +
                    ", topnews=" + topnews +
                    '}';
        }
    }

    /**
     * 新闻
     */
    public class NewsTagData_News {
        public String listimage;
        public String title;
        public String type;
        public String url;
        public String pubdate;

        @Override
        public String toString() {
            return "NewsTagData_News{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    /**
     * 头条新闻
     */
    public class NewsTagData_TopNews {
        public String topimage;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "NewsTagData_TopNews{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsDetailData{" +
                "data=" + data +
                '}';
    }
}

