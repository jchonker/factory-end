package com.factory.end.dto.primary;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/21 15:41
 * @Version 1.0
 */
@Data
public class UserDto implements UserDetails {
    private Long id;

    private String username;

    private String password;

    private String roles;

    private boolean enable;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    private boolean accountNonExpired;

    private List<GrantedAuthority> authorities;

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setAuthorities(List<GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
