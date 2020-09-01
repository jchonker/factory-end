package com.factory.end.service;

import com.factory.end.dto.second.UserDto;
import com.factory.end.service.second.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/21 16:12
 * @Version 1.0
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    //@Autowired
    //private UserDao userDao;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库尝试读取该用户
        UserDto userDto = userService.findByUserName(username);
        System.out.println("从数据库尝试读取该用户:"+userDto);
        //用户不存在,抛出异常
        if(userDto == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //将数据库形式的roles解析为UserDetails的权限集
        //AuthorityUtils.commaSeparatedStringToAuthorityList是Spring Security提供的,该方法用于将逗号隔开的权限集字符串切割为可用的权限对象列表
        //当然也可以自己实现,如用分号隔离开,参考generateAuthorities
        userDto.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(userDto.getRoles()));
        System.out.println("填充authorityList后的userDto:"+userDto);
        return userDto;
    }

    /**
     * 自行实现
     * 此方法暂时没用到
     * @param roles
     * @return
     */
    private List<GrantedAuthority> generateAuthoritys(String roles){
        List<GrantedAuthority> authorities = new ArrayList<>();
        String[] rolesArray = roles.split(":");
        if(roles != null && !"".equals(roles)){
            for(String role : rolesArray){
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }
}
