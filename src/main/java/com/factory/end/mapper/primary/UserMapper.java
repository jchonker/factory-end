package com.factory.end.mapper.primary;

import com.factory.end.model.primary.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/21 15:47
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends CrudRepository<User,Integer>, JpaSpecificationExecutor<User> {

    //@Select({"select * from users where username = #{username}"})
    //nativeQuery使用原生sql
    /**
     * 添加自定义方法
     * 根据用户名查询所有用户信息
     * @param username
     * @return
     */
    @Query(value = "select id,username,password,enable,roles from pro_User where username = ?1",nativeQuery = true)
    User selectUserByUserName(@Param("username") String username);

    /**
     * 修改用户名是否可用
     * @param username
     * @param enable
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_User set enable = :enable where username = :username",nativeQuery = true)
    void updateEnableByUserName(@Param("username") String username, @Param("enable") Integer enable);

    /**
     * 根据用户名修改密码
     * @param username
     * @param password
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_User set password = :password where username = :username",nativeQuery = true)
    void updatePasswordByUserName(@Param("username") String username,@Param("password") String password);

    /**
     * 根据用户名删除用户记录
     * @param username
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "delete from pro_User where username = :username",nativeQuery = true)
    void deleteUserByUsername(@Param("username") String username);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);

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
