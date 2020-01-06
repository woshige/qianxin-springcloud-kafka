package com.qianxin.mapper;


import com.qianxin.bean.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insert(UserInfoBean userInfoBean);

    UserInfoBean findById(String userId);

    void updateById(String userId);

    void delete(String userId);
}
