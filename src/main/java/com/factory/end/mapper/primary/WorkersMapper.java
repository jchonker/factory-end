package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Workers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:39
 * @Version 1.0
 */
@Mapper
public interface WorkersMapper extends CrudRepository<Workers,String>, JpaSpecificationExecutor<Workers> {

}
