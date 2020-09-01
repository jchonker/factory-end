package com.factory.end.service.second.impl;

import com.factory.end.mapper.second.IUserMapper;
import com.factory.end.dto.second.UserDto;
import com.factory.end.model.second.User;
import com.factory.end.service.second.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jchonker
 * @Date 2020/8/21 15:53
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserDao userDao;

    @Autowired
    private IUserMapper iUserMapper;
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
