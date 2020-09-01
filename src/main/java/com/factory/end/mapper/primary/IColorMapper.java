package com.factory.end.mapper.primary;

import com.factory.end.model.primary.Color;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author jchonker
 * @Date 2020/8/28 13:14
 * @Version 1.0
 */
@Mapper
public interface IColorMapper extends CrudRepository<Color,Integer> {

    /**
     * 根据设备号查询对应颜色数据
     * @param equNo
     * @return
     */
    Color findColorByEquipmentNo(Integer equNo);

}
