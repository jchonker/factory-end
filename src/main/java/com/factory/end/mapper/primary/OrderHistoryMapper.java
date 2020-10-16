package com.factory.end.mapper.primary;

import com.factory.end.model.primary.OrderHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

/**
 * @Author jchonker
 * @Date 2020/10/16 14:31
 * @Version 1.0
 */
@Mapper
public interface OrderHistoryMapper extends CrudRepository<OrderHistory,Integer>, JpaSpecificationExecutor<OrderHistory> {
    /**
     * 根据完成时间分组查询
     * @return
     */
    @Query(value = "select CONVERT(char(10),Comp_Order_Date,120) as Date,Lot_Name,Kind_Name,count(*) as count from pro_Order_History group by CONVERT(char(10),Comp_Order_Date,120),Lot_Name,Kind_Name",nativeQuery = true)
    List<Map<String,Map<String,Map<String,Integer>>>> findOrderHistoriesGroupByCompOrderDate();

}
