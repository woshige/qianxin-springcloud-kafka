package com.qianxin.feign;

import com.qianxin.bo.ConsumerBO;
import com.qianxin.common.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("server3")
public interface ConsumerFeign {
    @RequestMapping(value = "server3",method = RequestMethod.GET)
    Result<ConsumerBO> findById(@PathVariable("userId") String userId);
    @RequestMapping(value = "server3",method = RequestMethod.PUT)
    Result insert(ConsumerBO consumerBO);
    @RequestMapping(value = "server3",method = RequestMethod.POST)
    Result update(@PathVariable("userId")String userId);
    @RequestMapping(value = "server3",method = RequestMethod.DELETE)
    Result deleteById(@PathVariable("userId")String userId);
}
