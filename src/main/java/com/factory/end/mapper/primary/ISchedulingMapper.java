package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Product;
import com.factory.end.model.primary.Scheduling;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:36
 * @Version 1.0
 */
@Mapper
public interface ISchedulingMapper extends CrudRepository<Scheduling,Integer>, JpaSpecificationExecutor<Scheduling> {
    /**
     * 根据订单号查询所有数据
     * findAllByOrderNo
     * @param orderNo
     * @return
     */
    Scheduling findByOrderNo(String orderNo);

    /**
     * 根据设备号查询所有数据
     * @param equNo
     * @return
     */
    List<Scheduling> findAllByEquipmentNo(String equNo);

    /**
     * 根据产品型号查询
     * @param kindName
     * @return
     */
    List<Scheduling> findAllByKindName(String kindName);

    /**
     * 根据产品类型查询
     * @param kindClass
     * @return
     */
    List<Scheduling> findAllByKindClass(String kindClass);

    /**
     * 根据产品名称查询
     * @param lotName
     * @return
     */
    List<Scheduling> findAllByLotName(String lotName);

    /**
     * 根据下单人员名查询
     * @param userName
     * @return
     */
    List<Scheduling> findAllByUserName(String userName);

    /**
     * 根据下单时间段查询
     * @param startTime
     * @param endTime
     * @return
     */
    List<Scheduling> findAllByOrderPlaceDateBetween(String startTime,String endTime);

    /**
     * 根据生产顺序查询
     * @param manuOrder
     * @return
     */
    List<Scheduling> findAllByManuOrder(Integer manuOrder);

    /**
     * 根据订单号删除订单
     * @param orderNo
     * @return
     */
    Integer deleteByOrderNo(String orderNo);

    /**
     * 根据下单人员删除
     * @param username
     * @return
     */
    Integer deleteByUserName(String username);

    /**
     * 根据订单号查询订单是否存在
     * @param orderNo
     * @return
     */
    boolean existsByOrderNo(String orderNo);

    /**
     * 根据下单人员名查询订单是否存在
     * @param username
     * @return
     */
    boolean existsByUserName(String username);

    /**
     * 根据顺序排序根据设备号查询查询第1条记录
     * @param equNo
     * @return
     */
    @Query(value = "select top 1 * from pro_Scheduling where Equipment_No = :equNo order by Manu_Order;",nativeQuery = true)
    Scheduling findOneByEquipmentNoOrderByManuOrder(String equNo);
}
