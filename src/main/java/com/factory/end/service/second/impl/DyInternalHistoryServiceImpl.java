package com.factory.end.service.second.impl;

import com.factory.end.mapper.second.DyInternalHistoryMapper;
import com.factory.end.dto.second.DyInternalHistoryDto;
import com.factory.end.model.second.DyInternalHistory;
import com.factory.end.service.second.DyInternalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author jchonker
 * @Date 2020/8/13 16:25
 * @Version 1.0
 */
@Service
public class DyInternalHistoryServiceImpl implements DyInternalHistoryService {

    @Autowired
    private DyInternalHistoryMapper iDyInternalHistoryMapper;

    //定义一个ehchache缓存名
    //private final String CACHE_NAME = "cacheTest";

    @Override
    public List<DyInternalHistoryDto> findAll() {
        System.out.println("service.....查询所有数据");
        List<DyInternalHistoryDto> dyInternalHistoryDtos = new ArrayList<>();
        List<DyInternalHistory> dyInternalHistoryList = (List<DyInternalHistory>)iDyInternalHistoryMapper.findAll();

        DyInternalHistoryDto dyInternalHistoryDto = null;
        if(dyInternalHistoryList != null){
            for (DyInternalHistory dyInternalHistory: dyInternalHistoryList) {
                System.out.println("查询值:");
                System.out.println(dyInternalHistory.toString());
                dyInternalHistoryDto = entityToDto(dyInternalHistory);
                dyInternalHistoryDtos.add(dyInternalHistoryDto);
            }
        }else {
            System.out.println("查询无值");
        }

//        Iterable<DYInternalHistory> dyInternalHistoryDaoAll = dyInternalHistoryDao.findAll();
//        if (dyInternalHistoryDaoAll.iterator().hasNext()){
//            System.out.println("有值!");
//        }else {
//            System.out.println("无值!");
//        }
//        while (dyInternalHistoryDaoAll.iterator().hasNext()){
//            DYInternalHistory dyInternalHistory = dyInternalHistoryDaoAll.iterator().next();
//            System.out.println(dyInternalHistory.toString());
//        }

        return dyInternalHistoryDtos;
    }

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    @Override
    //@Cacheable(value = CACHE_NAME,key = "#p0")
    @Cacheable(value = "fpcache",key = "#id")
    public DyInternalHistoryDto findDYInternalHistoryById(Integer id) {
        Optional<DyInternalHistory> internalHistoryDaoById = iDyInternalHistoryMapper.findById(id);
        //DYInternalHistory dyInternalHistory = internalHistoryDaoById.get();
        DyInternalHistory dyInternalHistory = internalHistoryDaoById.isPresent() ? internalHistoryDaoById.get() : null;
        if(dyInternalHistory != null){
            System.out.println(dyInternalHistory.toString());
            DyInternalHistoryDto dyInternalHistoryDto = entityToDto(dyInternalHistory);
            return dyInternalHistoryDto;
        }
        return null;
    }


    /**
     * 将DYInternalHistory类型转换成DYInternalHistoryDto类型
     * @param dyInternalHistory
     * @return
     */
    private DyInternalHistoryDto entityToDto(DyInternalHistory dyInternalHistory) {
        DyInternalHistoryDto dyInternalHistoryDto;
        dyInternalHistoryDto = new DyInternalHistoryDto();
        dyInternalHistoryDto.setID(dyInternalHistory.getID());
        dyInternalHistoryDto.setBarcode(dyInternalHistory.getBarcode());
        dyInternalHistoryDto.setLot_ID(dyInternalHistory.getLot_ID());
        dyInternalHistoryDto.setLot_Name(dyInternalHistory.getLot_Name());
        dyInternalHistoryDto.setPrint_Date(dyInternalHistory.getPrint_Date());
        dyInternalHistoryDto.setPrint_Flag(dyInternalHistory.getPrint_Flag());
        dyInternalHistoryDto.setProduct_ID(dyInternalHistory.getProduct_ID());
        dyInternalHistoryDto.setProduct_Name(dyInternalHistory.getProduct_Name());
        dyInternalHistoryDto.setProduction_Date(dyInternalHistory.getProduction_Date());
        return dyInternalHistoryDto;
    }
}
