package com.factory.end.config;

import com.factory.end.service.MyUserDetailsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author jchonker
 * @Date 2020/8/21 14:38
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf防护
                .csrf().disable();
                //.headers().frameOptions().disable()
//        http
//                .antMatcher("/oauth/**").authorizeRequests()
//                .antMatchers("/oauth/**").permitAll()
//                .and();
        http
                //登录处理
                .formLogin()   //表单方式,或httpBasic
                .loginPage("/login")
                .loginProcessingUrl("/form")
                //成功登录后跳转请求接口
                .defaultSuccessUrl("/login_success")
                //登录失败页面请求接口
                .failureUrl("/login_fail");
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
//                .withUser("oauth").password(passwordEncoder().encode("123456")).roles("ADMIN")
//                .and()
//                .withUser("sang").password(passwordEncoder().encode("123456")).roles("USER");
    }
}
