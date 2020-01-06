package com.qianxin.mapper;

import com.qianxin.common.Result;
import com.qianxin.vo.ConsumerVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsumerMapper {
    Result insert(ConsumerVO consumerVO);
}
