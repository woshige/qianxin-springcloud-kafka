package com.qainxin;

import com.qianxin.ConsumerApplication;
import com.qianxin.bo.ProducerBO;
import com.qianxin.service.ConsumerInfoService;
import com.qianxin.vo.ConsumerVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class SpringKafkaTest {
    Logger logger = LoggerFactory.getLogger(SpringKafkaTest.class);
    @Autowired
    ConsumerInfoService consumerInfoService;
    @Autowired
    DataSource dataSource;
    @Test
    public void testInsertDb(){
        long start = System.currentTimeMillis();
        List<ConsumerVO> vos = new LinkedList<>();
        for (int i = 0; i < 80000; i++) {
            ConsumerVO vo = new ConsumerVO();
            ProducerBO producerBO = new ProducerBO();
            producerBO.setCreate_time(System.currentTimeMillis());
            producerBO.setDip("1.1.1.1");
            producerBO.setSip("2.2.2.2");
            producerBO.setThreat_level(Short.MIN_VALUE);
            vo.setUser_tel("18709299581");
            vo.setUser_name("slc");
            vo.setProducerBO(producerBO);
            vos.add(vo);
        }
        consumerInfoService.insert(vos);
        long end = System.currentTimeMillis();
        logger.info("入库80000条数据总共要花费{}ms",end - start);
    }
    @Test
    public void testDataSource(){
        logger.info("连接池为：{}",dataSource.getClass().getClasses().getClass());
    }
}
