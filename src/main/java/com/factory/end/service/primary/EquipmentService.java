package com.factory.end.service.primary;

import com.factory.end.dto.primary.EquipmentDto;
import com.factory.end.model.primary.Equipment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 14:50
 * @Version 1.0
 */
@Service
public interface EquipmentService {
    /**
     * 查询所有
     * @return
     */
    List<Equipment> findAll();

    /**
     * 保存
     * @param equipment
     */
    void save(Equipment equipment);

    /**
     * 根据设备号修改设备状态
     * @param equNo
     * @param equStatus
     */
    void updateEquStatusByEquNo(String equNo,Integer equStatus);

    /**
     * 根据设备号修改是否允许生产
     * @param equNo
     * @param allowProduct
     */
    void updateAllowProduct(String equNo,Integer allowProduct);

    /**
     * 查询空闲的设备记录
     * @return
     */
    List<Equipment> findAllOfLeisure();

    /**
     * 根据状态查询设备信息
     * @param equStatus
     * @param allowProduct
     * @param autoModel
     * @return
     */
    List<Equipment> findAllByAllStatus(Integer equStatus, Integer allowProduct, Integer autoModel);

}
