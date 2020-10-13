package com.factory.end.service.primary;

import com.factory.end.model.primary.Scheduling;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 17:03
 * @Version 1.0
 */
@Service
public interface SchedulingService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Scheduling findById(Integer id);

    /**
     * 查询所有数据
     * @return
     */
    List<Scheduling> findAll();

    /**
     * 根据订单号查询所有数据
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
     * 根据id删除
     * @param id
     * @return
     */
    void deleteById(Integer id);

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
     * 插入单条数据
     * @return
     */
    void saveScheduling(Scheduling scheduling);

    /**
     * 批量插入
     * @return
     */
    void saveSchedulings(List<Scheduling> schedulingList);

    /**
     * 根据顺序排序根据设备号查询查询第1条记录
     * @param equNo
     * @return
     */
    Scheduling findOneByEquipmentNoOrderByManuOrder(String equNo);

    /**
     * 复杂条件分页查询
     * @param order
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Scheduling> findByPage(Scheduling order, Integer currentPage, Integer pageSize);
}
