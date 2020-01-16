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
    public static String key = "QI_AN_XIN_PRACTICE";
    public static String TEST_KEY = "QI_AN_XIN_TEST_04";
    @Autowired
    private SendService service;

    @RequestMapping(method = RequestMethod.PUT)
    public Result send(@RequestBody ProducerBO entity) {
        return Result.build(service.send(key,JsonUtils.objectToJson(entity)));
    }

    /**
     * ./kafka-producer-perf-test.sh --topic test_for_producer --num-records 8000 --throughput -1 --record-size 1024 --producer.config ../config/producer.properties
     * 8000 records sent,
     * 10037.641154 records/sec (9.80 MB/sec),
     * 241.02 ms avg latency, 366.00 ms max latency, 235 ms 50th, 359 ms 95th, 366 ms 99th, 366 ms 99.9th.
     *
     *
     *
     * ./kafka-producer-perf-test.sh --topic test_for_producer --num-records 100000 --throughput -1 --record-size 1024 --producer.config ../config/producer.properties
     * 100000 records sent,
     * 39984.006397 records/sec (39.05 MB/sec), 555.14 ms avg latency, 821.00 ms max latency, 553 ms 50th, 764 ms 95th, 806 ms 99th, 818 ms 99.9th.
     * @param entity
     * @return
     */
    @RequestMapping(value = "/testForProducer", method = RequestMethod.PUT)
    public String testForProducer(@RequestBody final ProducerBO entity) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 80000; i++) {
            service.send(TEST_KEY,JsonUtils.objectToJson(entity));
        }
        Long end = System.currentTimeMillis();
        return "80000条数据总共耗费了" + (end - start) + "ms";
    }
}
