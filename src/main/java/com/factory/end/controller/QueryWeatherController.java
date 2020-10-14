package com.factory.end.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.factory.end.util.ip.IPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

        String ipAddr = IPUtils.getIpAddress(request);
        logger.info("ip地址:"+ipAddr);

        //1:获取访问者的真实ip地址
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
            String cip = jsonObject.get("cip").toString();
            logger.info("cip:"+cip);

            //2:根据ip地址获取到城市信息
            String cityUrl = "http://whois.pconline.com.cn/ipJson.jsp?callback=testJson&ip="+cip;
            ResponseEntity<String> cityEntity = restTemplate.getForEntity(cityUrl, String.class);
            if(200==cityEntity.getStatusCodeValue()){
                /**
                 * 获取到的字符串为：
                 *  if(window.testJson) {
                 * testJson(
                 * {"ip":"218.108.104.82","pro":"浙江省","proCode":"330000","city":"杭州市","cityCode":"330100","region":"","regionCode":"0","addr":"浙江省杭州市 华数宽带","regionNames":"","err":""}
                 * );
                 * }
                 * 形式,需要解析
                 */
                String entityBody = cityEntity.getBody();
                logger.info(entityBody);
                String startStr = "{";
                //"{" 第2次出现的位置
                int startOrdinal =  2;
                String endStr = "}";
                //"}" 第1次出现的位置
                int endOrdinal = 1;
                int startIndex = StrUtil.ordinalIndexOf(entityBody, startStr, startOrdinal);
                int endIndex = StrUtil.ordinalIndexOf(entityBody, endStr, endOrdinal);
                logger.info("startIndex:"+startIndex);
                logger.info("endIndex:"+endIndex);
                String cityJson = entityBody.substring(startIndex, endIndex + 1);
                logger.info("city:"+cityJson);

                JSONObject cityObject = JSON.parseObject(cityJson);
                String city =  cityObject.get("city").toString();
                logger.info("城市名:"+city);

                //3::根据城市名获取城市所在的天气信息
                String apiURL = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
                if(200==responseEntity.getStatusCodeValue()){
                    logger.info(responseEntity.getBody());
                    return responseEntity.getBody();
                }else{
                    return "error with code : " + responseEntity.getStatusCodeValue();
                }
            }
        }
        return "error with code : " + cityInfoEntity.getStatusCodeValue();
    }
}
