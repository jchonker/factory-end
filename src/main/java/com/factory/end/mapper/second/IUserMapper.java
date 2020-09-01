package com.factory.end.mapper.second;

import com.factory.end.model.second.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author jchonker
 * @Date 2020/8/21 15:47
 * @Version 1.0
 */
@Mapper
public interface IUserMapper extends CrudRepository<User,Integer> {

    //@Select({"select * from users where username = #{username}"})
    //nativeQuery使用原生sql
    /**
     * 添加自定义方法
     * 根据用户名查询所有用户信息
     * @param username
     * @return
     */
    @Query(value = "select * from users where username = ?1",nativeQuery = true)
    User selectUserByUserName(@Param("username") String username);
}
