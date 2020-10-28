package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/10/22 17:33
 * @Version 1.0
 */
@Mapper
public interface MenuMapper extends CrudRepository<Menu,Integer> {
    /**
     * 资源表和角色表关联查询
     * @return
     */
    @Query(value = "SELECT m.*,r.id AS rid,r.name AS rname,r.nameZh AS rnameZh\n" +
            "        FROM pro_Menu m\n" +
            "        LEFT JOIN pro_Menu_Role mr ON m.id=mr.mid\n" +
            "        LEFT JOIN pro_Role r ON mr.rid=r.id",nativeQuery = true)
    List<Menu> getAllMenus();
}
