package com.factory.end.service.primary;

import com.factory.end.model.primary.Color;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 13:17
 * @Version 1.0
 */
@Service
public interface ColorService {
    /**
     * 根据设备号查询颜色
     * @param equNo
     * @return
     */
    Color findColorByEquipmentNo(Integer equNo);

    /**
     * 查询所有颜色数据
     * @return
     */
    List<Color> findAllColor();
}
