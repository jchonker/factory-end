package com.factory.end.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation("指定城市获取添加天气")
    public String extern(HttpServletRequest request){
//        String ipAddr = IpUtil.getIpAddr(request);
//        logger.info("ip地址:"+ipAddr);

        //获取访问者的城市信息
        String citySN = "http://pv.sohu.com/cityjson";
        ResponseEntity<String> cityInfoEntity = restTemplate.getForEntity(citySN, String.class);
        if(200==cityInfoEntity.getStatusCodeValue()){
            String cityStr = cityInfoEntity.getBody();
            logger.info(cityStr);
            //获取到的字符串为var returnCitySN = {...}形式,截取括号中的数据
            int start = cityStr.indexOf('{');
            int end = cityStr.indexOf('}');
            String cityInfo = cityStr.substring(start, end+1);
            logger.info("cityInfo:"+cityInfo);
            JSONObject jsonObject = JSON.parseObject(cityInfo);
            String cname = jsonObject.get("cname").toString();
            logger.info("cname:"+cname);
            //获取省后面的低级市级城市名
            int index = cname.indexOf("省");
            String prefectureLevel = cname.substring(index+1);
            logger.info("地级市:"+prefectureLevel);

            //根据地级市获取城市所在的天气信息
            String apiURL = "http://wthrcdn.etouch.cn/weather_mini?city=" + prefectureLevel;
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
            if(200==responseEntity.getStatusCodeValue()){
                return responseEntity.getBody();
            }else{
                return "error with code : " + responseEntity.getStatusCodeValue();
            }
        }
        return "error with code : " + cityInfoEntity.getStatusCodeValue();
    }
}
