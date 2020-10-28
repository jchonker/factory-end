package com.factory.end.config.jwt;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author jchonker
 * @Date 2020/10/22 17:26
 * @Version 1.0
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    //在该方法中判断当前登录的用户是否具有当前请求URL所需要的角色信息，如果不具备，就抛出AccessDeniedException异常，否则不做任何事情
    /**
        参数：
        1. Authentication： 包含当前登录用户的信息
        2. Object： 是一个FilterInvocation对象，可以获取当前请求对象
        3. Collection<ConfigAttribute>： FilterInvocationSecurityMetadataSource中的getAttributes方法的返回值，即当前请求URL所需要的角色
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
        for (ConfigAttribute configAttribute : collection){
            if("ROLE_LOGIN".equals(configAttribute.getAttribute()) && authentication instanceof UsernamePasswordAuthenticationToken){
                //如果角色是"ROLE_LOGIN"，说明当前请求的URL用户登录后即可访问
                //如果auth是UsernamePasswordAuthenticationToken的实例，那么说明当前用户已登录，该方法到此结束，否则进入正常判断流程
                return;
            }
            //如果当前用户具备当前请求需的角色，方法结束。
            for (GrantedAuthority authority : auths){
                if (configAttribute.getAttribute().equals(authority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
