package com.factory.end.config;

import com.factory.end.config.jwt.JWTAuthenticationFilter;
import com.factory.end.config.jwt.JWTAuthorizationFilter;
import com.factory.end.service.MyUserDetailsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author jchonker
 * @Date 2020/8/21 14:38
 * @Version 1.0
 * 使用参考：
 * https://blog.csdn.net/I_am_Hutengfei/article/details/100561564
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;

    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;

    @Autowired
    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;

    @Autowired
    CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;

    /**
     * jwt相关bean
     */
//    @Autowired
//    JWTAuthenticationFilter jwtAuthenticationFilter;
//
//    @Autowired
//    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return myUserDetailsService;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                //关闭csrf防护
//                .csrf().disable()
//                .headers().frameOptions().disable();
//        http.authorizeRequests()
//                //登录接口放行
//                .antMatchers("/login").permitAll()
//                //swagger接口文档地址放行
//                .antMatchers(
//                        "/webjars/**",
//                        "/resources/**",
//                        "/swagger-ui.html",
//                        "/swagger-resources/**",
//                        "/v2/api-docs")
//                .permitAll()
//                //.antMatchers("/login_success").permitAll()
//                //.antMatchers("/login_fail").permitAll()
//                .antMatchers("/**").hasRole("ADMIN")
////                .anyRequest().authenticated()
//                .and()
//                //用户未登录获取登录状态过期失效处理
//                .exceptionHandling().
//                authenticationEntryPoint(customizeAuthenticationEntryPoint)
//                //登录
//                .and().
//                    formLogin().
//                    //允许所有用户
//                    permitAll().
//                    //登录成功处理逻辑
//                    successHandler(customizeAuthenticationSuccessHandler).
//                    //登录失败处理逻辑
//                    failureHandler(customizeAuthenticationFailureHandler)
//                //登出
//                .and().
//                    logout().
//                    permitAll().//允许所有用户
//                    //登出成功处理逻辑
//                    logoutSuccessHandler(customizeLogoutSuccessHandler).
//                    //登出之后删除cookie
//                    deleteCookies("JSESSIONID")
//                //会话管理
//                .and().
//                    sessionManagement().
//                    //限制同一个账号只能一个用户使用
//                    maximumSessions(1).
//                    //会话信息过期策略会话信息过期策略(账号被挤下线)
//                    expiredSessionStrategy(customizeSessionInformationExpiredStrategy);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //swagger接口文档放行
                .antMatchers(
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs")
                .permitAll()
                //登录放行
                .antMatchers("/login")
                .permitAll()
                //修改用户是否可用，只能是admin类型用户操作
                //查询所有用户，只能是admin类型用户操作
                .antMatchers("/user/enable/**","/user/listUser")
                .hasRole("ADMIN")
                //注册放行,登录放行
                .antMatchers("/user/registy/**","/user/login/**")
                .permitAll()
                //BI接口放行
                .antMatchers("/BI/**")
                .permitAll()
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/tasks/**")
                .authenticated()
                .antMatchers(HttpMethod.DELETE, "/tasks/**")
                .hasRole("ADMIN")
                // 其他接口都要认证
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customizeAuthenticationEntryPoint);
    }

    /**
     * 权限继承
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_HIGHUSER ROLE_HIGHUSER > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    /**
     * 配置加密器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 测试加密
     */
    @Test
    public void test(){
        String pwd = "123456";
        String encode = new BCryptPasswordEncoder().encode(pwd);
        System.out.println(encode);
    }

    /**
     * 配置用户名和密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                //配置自定义的UserDetailsService用来从数据库中根据用户名查询用户信息以及角色信息
                .userDetailsService(myUserDetailsService)
                .and()
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder());
    }
}
