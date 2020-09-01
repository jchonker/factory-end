package com.factory.end.service.primary;

import com.factory.end.model.primary.EquipmentWorkers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 9:10
 * @Version 1.0
 */
@Service
public interface EquipmentWorkersService {

    /**
     * 根据设备号查询职工编号（根据时间倒序排序取第一条）
     * @param equNo
     * @return
     */
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
