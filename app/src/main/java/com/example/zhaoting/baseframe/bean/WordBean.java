package com.example.zhaoting.baseframe.bean;

import java.util.List;

/**
 * Created by zhaoting on 16/7/28.
 * 顺眼天下bean
 */
public class WordBean {


    /**
     * guid : 63
     * main : <p></p>
     * type : post
     * author_name : null
     * related_guid : null
     * time_published : 1468827169
     * number_of_bookmarks : 0
     * number_of_shares : 0
     * number_of_comments : 0
     * number_of_reads : 7
     */

    /**
     * cursorBean
     */
    /**
     * info
     */

    private List<DataBean> data;
    private CursorBean cursor;

    public CursorBean getCursor() {
        return cursor;
    }

    public void setCursor(CursorBean cursor) {
        this.cursor = cursor;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String guid;
        private String main;
        private String type;
        private Object author_name;
        private Object related_guid;
        private String time_published;
        private String number_of_bookmarks;
        private String number_of_shares;
        private String number_of_comments;
        private String number_of_reads;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(Object author_name) {
            this.author_name = author_name;
        }

        public Object getRelated_guid() {
            return related_guid;
        }

        public void setRelated_guid(Object related_guid) {
            this.related_guid = related_guid;
        }

        public String getTime_published() {
            return time_published;
        }

        public void setTime_published(String time_published) {
            this.time_published = time_published;
        }

        public String getNumber_of_bookmarks() {
            return number_of_bookmarks;
        }

        public void setNumber_of_bookmarks(String number_of_bookmarks) {
            this.number_of_bookmarks = number_of_bookmarks;
        }

        public String getNumber_of_shares() {
            return number_of_shares;
        }

        public void setNumber_of_shares(String number_of_shares) {
            this.number_of_shares = number_of_shares;
        }

        public String getNumber_of_comments() {
            return number_of_comments;
        }

        public void setNumber_of_comments(String number_of_comments) {
            this.number_of_comments = number_of_comments;
        }

        public String getNumber_of_reads() {
            return number_of_reads;
        }

        public void setNumber_of_reads(String number_of_reads) {
            this.number_of_reads = number_of_reads;
        }
    }
}
