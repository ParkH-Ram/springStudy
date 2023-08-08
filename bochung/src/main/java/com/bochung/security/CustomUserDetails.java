package com.bochung.security;


import com.bochung.entity.Members;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails, Serializable {
    private final Members members;
    private Collection<GrantedAuthority> authorities;

    public CustomUserDetails(Members members) {
        this.members = members;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.members.getRoleKey()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return members.getPassword();
    }

    // 가져오는 유저의 이름을 정할 수 있다.
    @Override
    public String getUsername() {
        return members.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //이메일이 인증되어 있고 계정이 잠겨있지 않으면 true
        return true;
    }
}