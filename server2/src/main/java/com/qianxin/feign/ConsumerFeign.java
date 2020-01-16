package com.qianxin.feign;

import com.qianxin.bean.UserInfoBean;
import com.qianxin.common.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

@FeignClient("server3")
public interface ConsumerFeign {
    @RequestMapping(value = "/server3/{userId}", method = RequestMethod.GET)
    Result<UserInfoBean> findUserInfoById(@PathVariable("userId") Long userId);

    @RequestMapping(value = "/server3", method = RequestMethod.PUT)
    Result insert(UserInfoBean consumerBO);

    @RequestMapping(value = "/server3", method = RequestMethod.POST)
    Result update(@PathVariable("userId") Long userId);

    @RequestMapping(value = "/server3", method = RequestMethod.DELETE)
    Result deleteById(@PathVariable("userId") Long userId);

    @RequestMapping(value = "/server3/findByUserList", method = RequestMethod.PUT)
    Result<Map<Long,UserInfoBean>> findByUserIds(@RequestParam("list") Set<Long> list);
}
