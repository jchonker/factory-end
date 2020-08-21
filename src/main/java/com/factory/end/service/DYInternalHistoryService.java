package com.factory.end.service;

import com.factory.end.dto.DYInternalHistoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/13 16:24
 * @Version 1.0
 */
@Service
public interface DYInternalHistoryService {
    /**
     * 查询所有数据
     * @return
     */
    List<DYInternalHistoryDto> findAll();

    /**
     * 根据id查询数据
     * @return
     */
    DYInternalHistoryDto findDYInternalHistoryById(Integer id);
}
