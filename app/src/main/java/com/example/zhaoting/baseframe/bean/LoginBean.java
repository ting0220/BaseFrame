package com.example.zhaoting.baseframe.bean;

import java.util.List;

/**
 * Created by zhaoting on 16/4/21.
 */
public class LoginBean {
    /**
     * "access_token":"0186114ea30bc5b5178301f206eb0bbc",
     * "user_unique_key":"9986114ea30bc5e5178301f206eb0bab",
     * "expiration": 23435123232
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
        private String accessToken;
        private String userUniqueKey;
        private long expiration;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getUserUniqueKey() {
            return userUniqueKey;
        }

        public void setUserUniqueKey(String userUniqueKey) {
            this.userUniqueKey = userUniqueKey;
        }

        public long getExpiration() {
            return expiration;
        }

        public void setExpiration(long expiration) {
            this.expiration = expiration;
        }


    }
}
