package com.qianxin.controller;

import com.qianxin.bean.UserInfoBean;
import com.qianxin.common.Result;
import com.qianxin.enums.ResultEnum;
import com.qianxin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/server3")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public Result<UserInfoBean> findUserInfoById(@PathVariable("userId") Long userId) {
        return Result.success(userMapper.findById(userId));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result insert(UserInfoBean userInfoBean) {
        System.out.println(userInfoBean.toString());
        try {
            int result = userMapper.insert(userInfoBean);
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR);
        }
        return Result.build(ResultEnum.SUCCESS);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.POST)
    public Result update(@PathVariable("userId") String userId) {
        userMapper.updateById(userId);
        return Result.build(ResultEnum.SUCCESS);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("userId") String userId) {
        try {
            userMapper.delete(userId);
        } catch (Exception e) {
            return Result.build(ResultEnum.ERROR);
        }
        return Result.build(ResultEnum.SUCCESS);
    }

    @RequestMapping(value = "/findByUserList", method = RequestMethod.PUT)
    public Result<Map<Long, UserInfoBean>> findByUserIds(@RequestParam("list") Set<Long> set) {
        for (Long s : set) {
            System.out.println("收到的list为" + s);
        }
        List<UserInfoBean> userByList = userMapper.findUserByList(set);
        Map<Long, UserInfoBean> map = new HashMap<Long, UserInfoBean>();
        for (UserInfoBean userInfoBean : userByList) {
            map.put(userInfoBean.getUser_id(), userInfoBean);
        }
        return Result.success(map);
    }
}
