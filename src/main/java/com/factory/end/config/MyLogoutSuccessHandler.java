package com.factory.end.config;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author jchonker
 * @Date 2020/8/25 14:12
 * @Version 1.0
 * 退出成功的处理类
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
//    @Autowired
//    private TokenStore tokenStore;

    //@Autowired
    //private SecurityProperties securityProperties;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String accessToken = request.getParameter("access_token");
//        if(StringUtils.isNotBlank(accessToken)){
//            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
//            if(oAuth2AccessToken != null){
//                System.out.println("----access_token是："+oAuth2AccessToken.getValue());
//                tokenStore.removeAccessToken(oAuth2AccessToken);
//                OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
//                tokenStore.removeRefreshToken(oAuth2RefreshToken);
//                tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
//            }
//        }
//        //HttpUtils.writeSuccess(BaseResponse.createResponse(HttpStatusMsg.OK.getStatus(), "退出成功"), response);
//
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(JSON.toJSONString("退出成功"));


    }
}
