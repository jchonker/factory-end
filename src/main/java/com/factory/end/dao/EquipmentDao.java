package com.factory.end.dao;

import com.factory.end.model.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author jchonker
 * @Date 2020/8/14 9:42
 * @Version 1.0
 */
@Repository
public interface EquipmentDao extends CrudRepository<Equipment,Integer> {
}
