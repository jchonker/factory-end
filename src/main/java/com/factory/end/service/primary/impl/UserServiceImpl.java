package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.UserMapper;
import com.factory.end.dto.second.UserDto;
import com.factory.end.model.primary.User;
import com.factory.end.service.primary.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/21 15:53
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper iUserMapper;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public UserDto findByUserName(String username) {
        User user = iUserMapper.selectUserByUserName(username);
        System.out.println("user:"+user);
        UserDto userDto = entityToDto(user);
        return userDto;
    }

    /**
     * 注册用户
     * @param username 用户名
     * @param password 密码
     * @param roles 角色
     */
    @Override
    public void registry(String username,String password,String roles) {
        User user = new User();
        user.setUsername(username);
        user.setRoles(roles);
        //设置账户是否可用
        user.setEnable(true);
        //对密码加密
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        iUserMapper.save(user);
    }

    /**
     * 修改用户名是否可用
     * @param username
     * @param enable
     * @return
     */
    @Override
    public void updateEnableByUserName(String username, Integer enable) {
        iUserMapper.updateEnableByUserName(username, enable);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> listUser() {
        List<User> userList = (List<User>) iUserMapper.findAll();
        if(userList != null){
            return userList;
        }
        else {
            return null;
        }
    }

    @Override
    public void updatePasswordByUserName(String username, String password) {
        iUserMapper.updatePasswordByUserName(username,password);
    }

    @Override
    public void deleteUserByUsername(String username) {
        iUserMapper.deleteUserByUsername(username);
    }

    @Override
    public User findUserByUserName(String username) {
        User user = iUserMapper.findUserByUsername(username);
        if(user != null){
            return user;
        }
        else {
            return null;
        }
    }

    @Override
    public List<User> findUserByEnable(Integer enable) {
        List<User> userList = iUserMapper.findUserByEnable(enable);
        if(userList != null){
            return userList;
        }
        return null;
    }

    @Override
    public List<User> findUserByRoles(String roles) {
        List<User> userList = iUserMapper.findUserByRoles(roles);
        if(userList != null){
            return userList;
        }
        return null;
    }

    /**
     * 将模板类转换为实体类
     * @param user
     * @return
     */
    private UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        //注意:获取bool类型的数据的方法是isXxx()
        userDto.setEnable(user.isEnable());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
