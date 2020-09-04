package com.factory.end.controller.primary;

import com.factory.end.model.primary.EquipmentWorkers;
import com.factory.end.service.primary.EquipmentWorkersService;
import com.factory.end.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/27 15:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/equWorker")
@Api(description = "设备对应员工")
public class EquipmentWorkersController {
    private Logger logger = LoggerFactory.getLogger(EquipmentWorkersController.class);

    @Autowired
    private EquipmentWorkersService equipmentWorkersService;

    @Autowired
    Result result;

    @GetMapping("/selectWorkNoByENoOrderByWorkerDate/{equNo}")
    @ApiOperation(value = "根据设备名查询当前操作员")
    public Result selectWorkNoByENoOrderByWorkerDate(@PathVariable("equNo") String equNo){
        logger.info("equWorker/selectWorkNoByENoOrderByWorkerDate");
        logger.info("参数 equNo:"+equNo);
        if(equNo == null){
            return result.Success("设备号为空!");
        }
        int equNoInt = Integer.parseInt(equNo);
        String workerNo = equipmentWorkersService.selectWorkNoByENoOrderByWorkerDate(equNoInt);
        return result.Success(workerNo);
    }

    @GetMapping("/findEquipmentWorkersByEquipmentNo/{equNo}")
    @ApiOperation(value = "根据设备名查询所有历史操作员工")
    public Result findEquipmentWorkersByEquipmentNo(@PathVariable("equNo") String equNo){
        logger.info("equWorker/findEquipmentWorkersByEquipmentNo");
        if(equNo == null){
            return result.Success("设备号为空!");
        }
        int equNoInt = Integer.parseInt(equNo);
        List<EquipmentWorkers> equipmentWorkersByEquipmentNo = equipmentWorkersService.findEquipmentWorkersByEquipmentNo(equNoInt);
        return result.Success(equipmentWorkersByEquipmentNo);
    }

    @GetMapping("/findEquipmentWorkersByWorkersNo/{workerNo}")
    @ApiOperation(value = "根据操作员工查询操作过的厉害设备名")
    public Result findEquipmentWorkersByWorkersNo(@PathVariable("workerNo") String workerNo){
        logger.info("equWorker/findEquipmentWorkersByWorkersNo");
        if(workerNo == null){
            return result.Success("员工号为空!");
        }
        List<EquipmentWorkers> equipmentWorkersByWorkersNo = equipmentWorkersService.findEquipmentWorkersByWorkersNo(workerNo);
        return result.Success(equipmentWorkersByWorkersNo);
    }
}
