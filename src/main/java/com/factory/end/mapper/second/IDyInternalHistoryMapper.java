package com.factory.end.mapper.second;

import com.factory.end.model.second.DyInternalHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author jchonker
 * @Date 2020/8/13 10:04
 * @Version 1.0
 */
@Mapper
public interface IDyInternalHistoryMapper extends CrudRepository<DyInternalHistory,Integer> {

}
