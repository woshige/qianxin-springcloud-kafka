package com.qianxin.mapper;


import com.qianxin.bo.ConsumerBO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insert(ConsumerBO consumerBean);
    ConsumerBO findById(String userId);
    void updateById(String userId);
    void delete(String userId);
}
