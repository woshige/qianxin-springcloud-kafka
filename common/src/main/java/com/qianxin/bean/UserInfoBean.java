package com.qianxin.bean;

public class UserInfoBean {
    private Long user_id;
    private String userName;
    private String user_tel;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "user_id=" + user_id +
                ", userName='" + userName + '\'' +
                ", user_tel='" + user_tel + '\'' +
                '}';
    }
}
