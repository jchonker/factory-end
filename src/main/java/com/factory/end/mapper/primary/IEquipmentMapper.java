package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:30
 * @Version 1.0
 */
@Mapper
public interface IEquipmentMapper extends CrudRepository<Equipment,Integer> {

}
