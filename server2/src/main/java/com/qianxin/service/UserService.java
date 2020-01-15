package com.qianxin.service;

import com.qianxin.vo.ConsumerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    private final Integer INSERT_BATCH = 2000;
    private String INSERT_SQL = "INSERT INTO CONSUMER(threat_level,dip,user_name,user_id,sip,user_tel,create_time) VALUES (nextval('consumer_sequence'),?,?,?,?,?,?)";
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(List<ConsumerVO> consumerVOS) {
        for (int i = 0; i < consumerVOS.size(); i = i + INSERT_BATCH) {
            final List<ConsumerVO> list = consumerVOS.subList(i, i + INSERT_BATCH > consumerVOS.size() ? consumerVOS.size() : i + INSERT_BATCH);
            jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    ConsumerVO consumerVO = list.get(i);
                    preparedStatement.setInt(1,consumerVO.getProducerBO().getThreat_level());
                    preparedStatement.setString(2,consumerVO.getProducerBO().getDip());
                    preparedStatement.setString(3,consumerVO.getUser_name());
                    preparedStatement.setLong(4,consumerVO.getUser_id());
                    preparedStatement.setString(5,consumerVO.getProducerBO().getSip());
                    preparedStatement.setString(6,consumerVO.getUser_tel());
                    preparedStatement.setLong(7,consumerVO.getProducerBO().getCreate_time());
                }

                @Override
                public int getBatchSize() {
                    return list.size();
                }
            });
        }
    }
}
