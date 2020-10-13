package com.factory.end.mapper.primary;

import com.factory.end.model.primary.EquipmentWorkers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:32
 * @Version 1.0
 */
@Mapper
public interface EquipmentWorkersMapper extends CrudRepository<EquipmentWorkers,Integer> {

    /**
     * 根据设备号查询职工编号（根据时间倒序排序取第一条）
     * @param equNo
     * @return
     */
    @Query(value = "select TOP 1Workers_No from pro_Equipment_Workers where Equipment_No = ?1 ORDER BY Workers_Date desc; ",nativeQuery = true)
    String selectWorkNoByENoOrderByWorkerDate(@Param("equNo") Integer equNo);

    /**
     * 根据设备名查询所有历史操作员工
     * @param equNo
     * @return
     */
    List<EquipmentWorkers> findEquipmentWorkersByEquipmentNo(Integer equNo);

    /**
     * 根据操作员工查询操作过的厉害设备名
     * @param workersNo
     * @return
     */
    List<EquipmentWorkers> findEquipmentWorkersByWorkersNo(String workersNo);
}
