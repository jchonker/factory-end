package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.IColorMapper;
import com.factory.end.model.primary.Color;
import com.factory.end.service.primary.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 13:21
 * @Version 1.0
 */
@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private IColorMapper iColorMapper;

    /**
     * 根据设备号查询颜色
     * @param equNo
     * @return
     */
    @Override
    public Color findColorByEquipmentNo(Integer equNo) {
        Color colorByEquipmentNo = iColorMapper.findColorByEquipmentNo(equNo);
        if(colorByEquipmentNo != null){
            System.out.println(colorByEquipmentNo);
            return colorByEquipmentNo;
        }
        return null;
    }

    /**
     * 查询所有颜色数据
     * @return
     */
    @Override
    public List<Color> findAllColor() {
        List<Color> colors = (List<Color>)iColorMapper.findAll();
        if(colors != null){
            System.out.println(colors);
            return colors;
        }
        return null;
    }
}
