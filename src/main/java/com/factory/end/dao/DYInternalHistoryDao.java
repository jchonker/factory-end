package com.factory.end.dao;

import com.factory.end.dto.DYInternalHistoryDto;
import com.factory.end.model.DYInternalHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/13 10:04
 * @Version 1.0
 */
@Repository
public interface DYInternalHistoryDao extends CrudRepository<DYInternalHistory,Integer> {

}
