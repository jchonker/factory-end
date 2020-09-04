package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.IStatusMapper;
import com.factory.end.dto.primary.StatusDto;
import com.factory.end.model.primary.Status;
import com.factory.end.service.primary.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:46
 * @Version 1.0
 */
@Service
public class StatusServiceImpl implements StatusService {
    Logger logger = LoggerFactory.getLogger(StatusServiceImpl.class);

    @Autowired
    private IStatusMapper iStatusMapper;

    @Override
    public List<Status> findAll() {
        List<StatusDto> statusDtos = new ArrayList<>();
        Iterable<Status> statusList = iStatusMapper.findAll();
        logger.info(statusList.toString());
        return (List<Status>) statusList;
//        logger.info(statusList.toString());
//        //List<Status> statusList = (List<Status>) all;
//        StatusDto statusDto = null;
//        if(statusList != null){
//            System.out.println(statusList);
//            for(Status status:statusList){
//                statusDto = entityToDto(status);
//                statusDtos.add(statusDto);
//            }
//            return statusDtos;
//        }else {
//            System.out.println("查询statusDtos无值:");
//        }
//        return null;
    }

    /**
     * Status转换成StatusDto类
     * @param status
     * @return
     */
//    private StatusDto entityToDto(Status status) {
//        StatusDto statusDto;
//        statusDto = new StatusDto();
//        statusDto.setCollectValue(status.getCollectValue());
//        statusDto.setCompProductFlg(status.getCompProductFlg());
//        statusDto.setEquipmentNo(status.getEquipmentNo());
//        statusDto.setKindClass(status.getKindClass());
//        statusDto.setKindName(status.getKindName());
//        statusDto.setLastUpdate(status.getLastUpdate());
//        statusDto.setLotName(status.getLotName());
//        statusDto.setNgValue(status.getNgValue());
//        statusDto.setOkValue(status.getOkValue());
//        statusDto.setTargetValue(status.getTargetValue());
//        return statusDto;
//    }
}
