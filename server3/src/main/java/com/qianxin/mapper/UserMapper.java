package com.qianxin.mapper;


import com.qianxin.bean.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    int insert(UserInfoBean userInfoBean);

    UserInfoBean findById(Long userId);

    void updateById(String userId);

    void delete(String userId);

    List<UserInfoBean> findUserByList(Set<Long> set);
}
