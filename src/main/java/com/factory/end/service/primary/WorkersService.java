package com.factory.end.service.primary;

import com.factory.end.model.primary.Workers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/9/27 14:42
 * @Version 1.0
 */
@Service
public interface WorkersService {
    /**
     * 增加
     * @param workers
     */
    void save(Workers workers);

    /**
     * 删除
     * @param workers
     */
    void delete(Workers workers);

    /**
     * 修改
     * @param workers
     */
    void update(Workers workers);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Workers> findByPage(Integer currentPage, Integer pageSize);

    /**
     * 全部查询
     * @return
     */
    List<Workers> findAll();
}
