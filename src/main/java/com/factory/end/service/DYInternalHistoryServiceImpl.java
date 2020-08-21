package com.factory.end.service;

import com.factory.end.dao.DYInternalHistoryDao;
import com.factory.end.dto.DYInternalHistoryDto;
import com.factory.end.model.DYInternalHistory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DYInternalHistoryServiceImpl implements DYInternalHistoryService {

    @Autowired
    private DYInternalHistoryDao dyInternalHistoryDao;

    @Override
    public List<DYInternalHistoryDto> findAll() {
        System.out.println("service.....查询所有数据");
        List<DYInternalHistoryDto> dyInternalHistoryDtos = new ArrayList<>();
        List<DYInternalHistory> dyInternalHistoryList = (List<DYInternalHistory>)dyInternalHistoryDao.findAll();

        DYInternalHistoryDto dyInternalHistoryDto = null;
        if(dyInternalHistoryList != null){
            for (DYInternalHistory dyInternalHistory: dyInternalHistoryList) {
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
    public DYInternalHistoryDto findDYInternalHistoryById(Integer id) {
        Optional<DYInternalHistory> internalHistoryDaoById = dyInternalHistoryDao.findById(id);
        //DYInternalHistory dyInternalHistory = internalHistoryDaoById.get();
        DYInternalHistory dyInternalHistory = internalHistoryDaoById.isPresent() ? internalHistoryDaoById.get() : null;
        if(dyInternalHistory != null){
            System.out.println(dyInternalHistory.toString());
            DYInternalHistoryDto dyInternalHistoryDto = entityToDto(dyInternalHistory);
            return dyInternalHistoryDto;
        }
        return null;
    }

    /**
     * 将DYInternalHistory类型转换成DYInternalHistoryDto类型
     * @param dyInternalHistory
     * @return
     */
    private DYInternalHistoryDto entityToDto(DYInternalHistory dyInternalHistory) {
        DYInternalHistoryDto dyInternalHistoryDto;
        dyInternalHistoryDto = new DYInternalHistoryDto();
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
