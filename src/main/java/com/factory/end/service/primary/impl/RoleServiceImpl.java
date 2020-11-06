package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.RoleMapper;
import com.factory.end.model.primary.Role;
import com.factory.end.service.primary.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/11/5 13:19
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        List<Role> roleList = (List<Role>) roleMapper.findAll();
        if(roleList == null){
            return null;
        }
        logger.info(roleList.toString());
        return roleList;
    }
}
