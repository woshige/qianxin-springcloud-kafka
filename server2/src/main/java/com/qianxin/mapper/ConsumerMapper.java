package com.qianxin.mapper;

import com.qianxin.vo.ConsumerVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsumerMapper {
    void insert(ConsumerVO consumerVO);
}
