package com.factory.end.service.primary;

import com.factory.end.model.primary.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/11/5 13:14
 * @Version 1.0
 */
@Service
public interface RoleService {
    List<Role> findAll();
}
