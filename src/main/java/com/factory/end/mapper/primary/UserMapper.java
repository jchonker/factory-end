package com.factory.end.mapper.primary;

import com.factory.end.dto.primary.UserDto;
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
     * pro_User和pro_User_Role和pro_Role左连接查询
     * selectUserByUserName
     * @param username
     * @return
     */
    //@Query(value = "select id,username,password,enable,roles from pro_User where username = ?1",nativeQuery = true)
    @Query(value = "select * from pro_User where username = ?1",nativeQuery = true)
    //@Query(value = "select u.id,u.username,u.password,u.enable,r.name as 'roles' from pro_User u left join pro_User_Role ur on u.id = ur.uid left join pro_Role r on ur.rid = r.id where u.username = :username",nativeQuery = true)
    List<User> findUserByUsernameByJoin(@Param("username") String username);



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
     * 修改用户名是否未锁定
     * @param username
     * @param account_non_locked
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_User set account_non_locked = :account_non_locked where username = :username",nativeQuery = true)
    void updateAccountNonLockedByUserName(@Param("username") String username, @Param("account_non_locked") Integer account_non_locked);

    /**
     * 修改用户名是否未过期
     * @param username
     * @param account_non_expired
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_User set account_non_expired = :account_non_expired where username = :username",nativeQuery = true)
    void updateAccountNonExpiredByUserName(@Param("username") String username, @Param("account_non_expired") Integer account_non_expired);

    /**
     * 修改密码是否未过期
     * @param username
     * @param credentials_non_expired
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_User set credentials_non_expired = :credentials_non_expired where username = :username",nativeQuery = true)
    void updateCredentialsNonExpiredByUserName(@Param("username") String username, @Param("credentials_non_expired") Integer credentials_non_expired);

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
     * @param username 用户名
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
    List<User> findUserByEnable(boolean enable);

    /**
     * 根据角色查询
     * @param roles
     * @return
     */
//    List<User> findUserByRoles(String roles);
}
