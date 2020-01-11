package com.qianxin.controller;

import com.qianxin.bo.ProducerBO;
import com.qianxin.common.Result;
import com.qianxin.service.SendService;
import com.qianxin.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server1")
public class ProduceController {
    @Autowired
    private SendService service;

    @RequestMapping(method = RequestMethod.PUT)
    public Result send(@RequestBody ProducerBO entity) {
        return Result.build(service.send(JsonUtils.objectToJson(entity)));
    }
    @RequestMapping(value = "/test",method = RequestMethod.PUT)
    public Result send() {
        return Result.build(service.send("woshislc"));
    }

    @RequestMapping(value = "/testForProducer", method = RequestMethod.PUT)
    public String testForProducer(@RequestBody final ProducerBO entity) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 80000; i++) {
            service.send(JsonUtils.objectToJson(entity));
        }
        Long end = System.currentTimeMillis();
        return "80000条数据总共耗费了" + (end - start) + "ms";
    }
}
