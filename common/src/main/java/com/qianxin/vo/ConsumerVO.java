package com.qianxin.vo;

/**
 *
 */
public class ConsumerVO {
    private Long id;
    private Short threat_level;
    private Long create_time;
    private String sip;
    private String dip;
    private Long user_id;
    private String user_name;
    private String user_tel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getThreat_level() {
        return threat_level;
    }

    public void setThreat_level(Short threat_level) {
        this.threat_level = threat_level;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    @Override
    public String toString() {
        return "ConsumerBean{" +
                "id=" + id +
                ", threat_level=" + threat_level +
                ", create_time=" + create_time +
                ", sip='" + sip + '\'' +
                ", dip='" + dip + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_tel='" + user_tel + '\'' +
                '}';
    }
}
