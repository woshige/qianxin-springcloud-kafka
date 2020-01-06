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
@RequestMapping("/server1/server1")
public class ProduceController {
    @Autowired
    private SendService service;

    @RequestMapping(method = RequestMethod.PUT)
    public Result send(@RequestBody ProducerBO entity) {
        return Result.build(service.send(JsonUtils.objectToJson(entity)));
    }
}
