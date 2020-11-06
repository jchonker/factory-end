package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.RoleMapper;
import com.factory.end.mapper.primary.UserMapper;
import com.factory.end.dto.primary.UserDto;
import com.factory.end.model.primary.Role;
import com.factory.end.model.primary.User;
import com.factory.end.service.primary.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author jchonker
 * @Date 2020/8/21 15:53
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper iUserMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 从角色列表中获取最大权限角色
     * 对id进行排序,最小的表示权限最大USER_ADMIN
     * @param roleList
     * @return
     */
    public String getMaxPermissions(List<Role> roleList){
        //得到权限列表中最大权限
        List<Role> role_list = new ArrayList<>();

        Iterator<Role> iterator = roleList.iterator();
        while (iterator.hasNext()){
            Role role = iterator.next();
            Integer id = role.getId();
            String name = role.getName();
            String nameZh = role.getNameZh();
            Role roleTmp = new Role();
            roleTmp.setId(id);
            roleTmp.setName(name);
            roleTmp.setNameZh(nameZh);
            role_list.add(roleTmp);
        }
        Collections.sort(roleList);
        logger.info("排序后:");
        logger.info(role_list.toString());
        String roles = role_list.get(0).getName();
        return  roles;
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
        List<Role> roleList = user.getRoleList();
        String maxPermissions = getMaxPermissions(roleList);
        userDto.setRoles(maxPermissions);
        userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
        userDto.setAccountNonLocked(user.isAccountNonLocked());
        userDto.setAccountNonExpired(user.isAccountNonExpired());
        return userDto;
    }


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public UserDto findByUserName(String username) {
        User user = iUserMapper.findUserByUsername(username);
        if(user != null){
            logger.info("user:"+user);
            UserDto userDto = entityToDto(user);
            return userDto;
        }
        return null;
    }

    /**
     * 注册用户
     * @param username 用户名
     * @param password 密码
     * @param roles 角色
     */
    @Override
    public void registry(String username,String password,Integer roles) {
        //先插入到用户表，并返回结果对象
        User user = new User();
        user.setUsername(username);
        //对密码加密
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        //设置账户是否可用
        user.setEnable(true);
        //设置账户未过期
        user.setAccountNonExpired(true);
        //设置账户未锁定
        user.setAccountNonLocked(true);
        //设置密码未过期
        user.setCredentialsNonExpired(true);

        //根据roles值确定要插入的值
        List<Integer> rolesIdList = new ArrayList<>();
        if(roles == 1){
            rolesIdList.add(1);
            rolesIdList.add(2);
            rolesIdList.add(3);
        }else if(roles == 2){
            rolesIdList.add(2);
            rolesIdList.add(3);
        }else if(roles == 3){
            rolesIdList.add(3);
        }

        List<Role> roleList = new ArrayList<>();
        Iterator<Integer> role_id_iterator = rolesIdList.iterator();
        while (role_id_iterator.hasNext()){
            Role role = roleMapper.findById(role_id_iterator.next()).get();
            roleList.add(role);
        }
        //设置用户关联的角色列表
        user.setRoleList(roleList);

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

    @Override
    public void updateAccountNonLockedByUserName(String username, Integer account_non_locked) {
        iUserMapper.updateAccountNonLockedByUserName(username,account_non_locked);
    }

    @Override
    public void updateAccountNonExpiredByUserName(String username, Integer account_non_expired) {
        iUserMapper.updateAccountNonExpiredByUserName(username,account_non_expired);
    }

    @Override
    public void updateCredentialsNonExpiredByUserName(String username, Integer credentials_non_expired) {
        iUserMapper.updateCredentialsNonExpiredByUserName(username,credentials_non_expired);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserDto> listUser() {
        List<User> userList = (List<User>) iUserMapper.findAll();
        System.out.println("userList:"+userList);
        //拷贝获取到的对象,防止操作原对象影响到数据库
        List<User> newUserList = userList;
        List<UserDto> userDtoList = new ArrayList<>();
        if(userList != null){
            for(User user:newUserList){
                List<Role> roleList = user.getRoleList();
                String maxPermissions = getMaxPermissions(roleList);
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUsername(user.getUsername());
                userDto.setPassword(user.getPassword());
                userDto.setEnable(user.isEnable());
                userDto.setRoles(maxPermissions);
                userDto.setAccountNonExpired(user.isAccountNonExpired());
                userDto.setAccountNonLocked(user.isAccountNonLocked());
                userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
                userDtoList.add(userDto);
            }
            return userDtoList;
        }
        else {
            return null;
        }

    }

    /**
     * 根据用户名修改密码
     * @param username
     * @param password
     */
    @Override
    public void updatePasswordByUserName(String username, String password) {
        iUserMapper.updatePasswordByUserName(username,password);
    }

    /**
     * 根据用户名删除用户
     * @param username
     */
    @Override
    public void deleteUserByUsername(String username) {
        User user = iUserMapper.findUserByUsername(username);
        iUserMapper.delete(user);
    }

    @Override
    public List<User> findUserByEnable(Integer enable) {
//        List<User> userList = null;
//        if(enable == 1){
//            userList = iUserMapper.findUserByEnable(true);
//        }else if(enable == 0){
//            userList = iUserMapper.findUserByEnable(false);
//        }
//        if(userList != null){
//            return userList;
//        }
        return null;
    }

    @Override
    public List<User> findUserByRoles(String roles) {
//        List<User> userList = iUserMapper.findUserByRoles(roles);
//        if(userList != null){
//            return userList;
//        }
        return null;
    }

}
