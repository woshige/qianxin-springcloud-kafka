package com.qianxin.vo;

import com.qianxin.bo.ProducerBO;

/**
 *
 */
public class ConsumerVO {
    private ProducerBO producerBO;
    private Long user_id;
    private String user_name;
    private String user_tel;

    public ProducerBO getProducerBO() {
        return producerBO;
    }

    public void setProducerBO(ProducerBO producerBO) {
        this.producerBO = producerBO;
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
}
