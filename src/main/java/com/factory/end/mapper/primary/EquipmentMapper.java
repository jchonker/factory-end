package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:30
 * @Version 1.0
 */
@Mapper
public interface EquipmentMapper extends CrudRepository<Equipment,Integer> {
    /**
     * 根据设备号修改设备状态
     * @param equNo
     * @param equStatus
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_Equipment set Equipment_Status = :equStatus where Equipment_No = :equNo",nativeQuery = true)
    void updateEquStatusByEquNo(@Param("equNo") String equNo, @Param("equStatus") Integer equStatus);

    /**
     * 根据设备号修改是否允许生产
     * @param equNo
     * @param allowProduct
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_Equipment set Allow_Product = :allowProduct where Equipment_No = :equNo",nativeQuery = true)
    void updateAllowProduct(@Param("equNo") String equNo,@Param("allowProduct") Integer allowProduct);

    /**
     * 查询空闲的设备记录
     * @return
     */
    @Query(value = "select * from pro_Equipment where Equipment_Status = 1 and Allow_Product = 0 and Auto_Model = 1",nativeQuery = true)
    List<Equipment> findAllOfLeisure();

    /**
     * 根据状态查询设备信息
     * @param equStatus  设备状态
     * @param allowProduct  是否允许生产
     * @param autoModel   是否自动生产
     * @return
     */
    @Query(value = "select * from pro_Equipment where Equipment_Status = :equStatus and Allow_Product = :allowProduct and Auto_Model = :autoModel",nativeQuery = true)
    List<Equipment> findAllByAllStatus(@Param("equStatus") Integer equStatus,@Param("allowProduct") Integer allowProduct,@Param("autoModel") Integer autoModel);
}
