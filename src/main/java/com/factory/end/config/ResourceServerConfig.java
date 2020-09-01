package com.factory.end.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author jchonker
 * @Date 2020/8/24 17:33
 * @Version 1.0
 * 配置资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("rid").stateless(true);
    }

    @Autowired
    private MyLogoutHandler myLogoutHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    /**
     * 此方法中要写内容,否则会出错
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()   //授权配置
                //放行所有请求
                .antMatchers("/**")
                .permitAll();
//                //角色大小写不一样ADMIN和admin
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/equipment/**").hasRole("ADMIN")
//                .antMatchers("/in/**").hasRole("ADMIN")
//                //放行swagger的接口
//                .antMatchers(
//                        "/webjars/**",
//                        "/resources/**",
//                        "/swagger-ui.html",
//                        "/swagger-resources/**",
//                        "/v2/api-docs")
//                .permitAll()
//                //用户登录刷新退出接口
//                .antMatchers("/identity/**")
//                .permitAll()
//                //放行websocket的访问接口
//                .antMatchers("/imserver/**")
//                .permitAll()
//                //放行oauth的接口
//                .antMatchers("/oauth/**")
//                .permitAll()
                //其他接口需要登录后才能访问
                //.anyRequest().authenticated();


//            .and()
//                .logout()
//                //退出登录的url,带着token参数
//                .logoutUrl("/logout")
//                //清除身份认证信息
//                .clearAuthentication(true)
//                //退出时的处理类,可以完成数据的清除工作,例如Cookie的清除
//                .addLogoutHandler(myLogoutHandler)
//                //退出成功处理LogoutSuccessHandler类,可以处理注销成功的业务逻辑,例如返回一段json提示或者跳转登录页面
//                .logoutSuccessHandler(myLogoutSuccessHandler)
//            .and()
//                .csrf().disable();

    }


}
