package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.ColorMapper;
import com.factory.end.model.primary.Color;
import com.factory.end.service.primary.ColorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(ColorServiceImpl.class);

    @Autowired
    private ColorMapper iColorMapper;

    /**
     * 根据设备号查询颜色
     * @param equNo
     * @return
     */
    @Override
    public Color findColorByEquipmentNo(Integer equNo) {
        Color colorByEquipmentNo = iColorMapper.findColorByEquipmentNo(equNo);
        if(colorByEquipmentNo != null){
            logger.info(colorByEquipmentNo.toString());
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
            logger.info(colors.toString());
            return colors;
        }
        return null;
    }
}
