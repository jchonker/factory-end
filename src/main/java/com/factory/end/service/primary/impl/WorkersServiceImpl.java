package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.WorkersMapper;
import com.factory.end.model.primary.Workers;
import com.factory.end.service.primary.WorkersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author jchonker
 * @Date 2020/9/27 14:50
 * @Version 1.0
 */
@Service
public class WorkersServiceImpl implements WorkersService {
    private Logger logger = LoggerFactory.getLogger(WorkersServiceImpl.class);

    @Autowired
    private WorkersMapper workersMapper;

    @Override
    public void save(Workers workers) {
        workersMapper.save(workers);
    }

    @Override
    public void delete(Workers workers) {
        workersMapper.delete(workers);
    }

    @Override
    public void update(Workers workers) {
        workersMapper.save(workers);
    }

    @Override
    public Page<Workers> findByPage(Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        //复杂条件查询
        return workersMapper.findAll(null,pageable);
    }

    @Override
    public List<Workers> findAll() {
        List<Workers> workersList = (List<Workers>) workersMapper.findAll();
        return workersList;
    }
}
