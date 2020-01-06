package com.qianxin.bo;

public class ProducerBO {
    private Short threat_level;
    private Long create_time;
    private String sip;
    private String dip;
    private Long user_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    @Override
    public String toString() {
        return "ProducerBean{" +
                "threat_level=" + threat_level +
                ", create_time=" + create_time +
                ", sip='" + sip + '\'' +
                ", dip='" + dip + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
