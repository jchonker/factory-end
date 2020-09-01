package com.factory.end.mapper.second;

import com.factory.end.model.second.MyEquipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author jchonker
 * @Date 2020/8/14 9:42
 * @Version 1.0
 */
@Mapper
public interface IMyEquipmentMapper extends CrudRepository<MyEquipment,Integer> {
}
