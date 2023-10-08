package com.chicken.security;

import com.chicken.entity.MemberInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CustomMemberDetails implements UserDetails, Serializable {
    private final MemberInfo memberInfo;
    private Collection<GrantedAuthority> authorities;

    public CustomMemberDetails(MemberInfo memberInfo){
        this.memberInfo = memberInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.memberInfo.getRoleKey()));  // 권한  저장
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberInfo.getMemberPassword();
    }

    //가져오는 유저 아이디 지정가능
    @Override
    public String getUsername(){
        return memberInfo.getMemberId();
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
        // 유저 아이디 있고  인증되어 있고 계정이 잠겨있지 않으면 true
        return true;
    }

}
