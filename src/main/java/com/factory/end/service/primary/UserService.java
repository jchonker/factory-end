package com.factory.end.service.primary;

import com.factory.end.dto.primary.UserDto;
import com.factory.end.model.primary.User;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 保存用户
     * @param username 用户名
     * @param password 密码
     * @param roles 角色
     */
    void registry(String username,String password,Integer roles);

    /**
     * 修改用户名是否可用
     * @param username
     * @param enable
     * @return
     */
    void updateEnableByUserName(String username,Integer enable);

    /**
     * 修改用户名是否未锁定
     * @param username
     * @param account_non_locked
     * @return
     */
    void updateAccountNonLockedByUserName(String username, Integer account_non_locked);

    /**
     * 修改用户名是否未过期
     * @param username
     * @param account_non_expired
     * @return
     */
    void updateAccountNonExpiredByUserName(String username, Integer account_non_expired);

    /**
     * 修改密码是否未过期
     * @param username
     * @param credentials_non_expired
     * @return
     */
    void updateCredentialsNonExpiredByUserName(String username, Integer credentials_non_expired);


    /**
     * 查询所有用户名
     * @return
     */
    List<UserDto> listUser();

    /**
     * 根据用户名修改密码
     * @param username
     * @param password
     */
    void updatePasswordByUserName( String username, String password);

    /**
     * 根据用户名删除用户记录
     * @param username
     */
    void deleteUserByUsername(String username);


    /**
     * 根据账号是否可用进行查询
     * @param enable
     * @return
     */
    List<User> findUserByEnable(Integer enable);

    /**
     * 根据角色查询
     * @param roles
     * @return
     */
    List<User> findUserByRoles(String roles);
}
