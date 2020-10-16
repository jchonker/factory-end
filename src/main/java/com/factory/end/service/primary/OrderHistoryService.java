package com.factory.end.service.primary;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author jchonker
 * @Date 2020/10/16 14:37
 * @Version 1.0
 */
@Service
public interface OrderHistoryService {
    /**
     * 根据完成时间分组查询
     * @return
     */
    List<Map<String,Map<String,Map<String,Integer>>>> findOrderHistoriesGroupByCompOrderDate();
}
