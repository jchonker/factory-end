package com.factory.end.controller;

import com.factory.end.util.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author jchonker
 * @Date 2020/10/9 10:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/city")
@Api(description = "获取城市的天气信息")
public class QueryWeatherController {
    private Logger logger = LoggerFactory.getLogger(QueryWeatherController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/{city}", method = RequestMethod.GET)
    @ApiOperation("指定城市获取添加天气")
    public String extern(HttpServletRequest request, @PathVariable("city") String city){
        String ipAddr = IpUtil.getIpAddr(request);
        logger.info("ip地址:"+ipAddr);

        String apiURL = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);

        if(200==responseEntity.getStatusCodeValue()){
            return responseEntity.getBody();
        }else{
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }
}
