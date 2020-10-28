package com.factory.end.config.jwt;

import com.factory.end.mapper.primary.MenuMapper;
import com.factory.end.model.primary.Menu;
import com.factory.end.model.primary.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/10/22 17:23
 * @Version 1.0
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    //用于实现ant风格的URL匹配
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    MenuMapper menuMapper;

    /**
     * Collection<ConfigAttribute>表示当前请求URL所需的角色
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        //获取当前请求的URL
//        String requestUrl = ((FilterInvocation) o).getRequestUrl();
//        //获取数据库中的资源信息，一般放在Redis缓存中
//        List<Menu> allMenus = menuMapper.getAllMenus();
//        //遍历信息，遍历过程中获取当前请求的URL所需要的角色信息并返回
//        for (Menu menu : allMenus){
//            if(antPathMatcher.match(menu.getPattern(), requestUrl)){
//                List<Role> roles = menu.getRoles();
//                String[] roleArr = new String[roles.size()];
//                for (int i = 0; i < roleArr.length; i++){
//                    roleArr[i] = roles.get(i).getName();
//                }
//                return SecurityConfig.createList(roleArr);
//            }
//        }
//        //如果当前请求的URL在资源表中不存在响应的模式，就假设该请求登录后即可访问，直接返回ROLE_LOGIN
//        return SecurityConfig.createList("ROLE_LOGIN");
        return null;
    }


    /**
     * getAllConfigAttributes()方法用来返回所有定义好的权限资源，SpringSecurity在启动时会校验相关配置是否正确
     * 如果不需要校验，直接返回null即可
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    /**
     * supports方法返回类对象是否支持校验
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
