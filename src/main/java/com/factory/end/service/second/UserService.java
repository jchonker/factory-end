package com.factory.end.service.second;

import com.factory.end.dto.second.UserDto;
import org.springframework.stereotype.Service;

/**
 * @Author jchonker
 * @Date 2020/8/21 15:52
 * @Version 1.0
 */
@Service
public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    UserDto findByUserName(String username);
}
