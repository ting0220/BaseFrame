package com.example.zhaoting.baseframe.bean;

/**
 * Created by zhaoting on 16/4/21.
 */
public class LoginBean {

    /**
     * access_token : 87221f079f3c500506fab630c57a56fe939fcafd
     * user_unique_key : ckI8OarTJJgwLhiDLas4snspCzsPg2pM
     * expiration : 1477106692
     * user_guid : 1000839
     */

    private String access_token;
    private String user_unique_key;
    private String expiration;
    private String user_guid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUser_unique_key() {
        return user_unique_key;
    }

    public void setUser_unique_key(String user_unique_key) {
        this.user_unique_key = user_unique_key;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getUser_guid() {
        return user_guid;
    }

    public void setUser_guid(String user_guid) {
        this.user_guid = user_guid;
    }
}
