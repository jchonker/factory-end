package com.factory.end.service.second;

import com.factory.end.dto.second.DyInternalHistoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/13 16:24
 * @Version 1.0
 */
@Service
public interface DyInternalHistoryService {
    /**
     * 查询所有数据
     * @return
     */
    List<DyInternalHistoryDto> findAll();

    /**
     * 根据id查询数据
     * @return
     */
    DyInternalHistoryDto findDYInternalHistoryById(Integer id);
}
