package com.tofirst.study.zhbj.activity.bean;

import java.util.ArrayList;

/**
 * 数据解析的实体类
 */
public class NewsData {
    public int retconde;
    public ArrayList<NewsMenuData> data;

    public class NewsMenuData{
        public int type;
        public String id;
        public String url;
        public String title;
        public ArrayList<NewsTagData> children;

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "title='" + title + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    public class NewsTagData {
        public int type;
        public String id;
        public String url;
        public String title;

        @Override
        public String toString() {
            return "NewsTagData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "data=" + data +
                '}';
    }
}
