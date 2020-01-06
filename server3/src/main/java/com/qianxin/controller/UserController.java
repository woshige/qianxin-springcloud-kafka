package com.qianxin.controller;

import com.qianxin.bo.ConsumerBO;
import com.qianxin.common.Result;
import com.qianxin.enums.ResultEnum;
import com.qianxin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server3")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public Result findUserInfoById(@PathVariable("userId") String userId) {
        return Result.success(userMapper.findById(userId));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result insert(ConsumerBO consumerBO) {
        try {
            userMapper.insert(consumerBO);

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
}