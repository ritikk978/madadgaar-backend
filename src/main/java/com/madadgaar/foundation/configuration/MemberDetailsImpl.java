package com.madadgaar.foundation.configuration;

import com.madadgaar.foundation.domain.enums.UserRole;
import com.madadgaar.foundation.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDetailsImpl implements UserDetails {

    private final String userName;

    private final List<GrantedAuthority> authorities;

    public MemberDetailsImpl(String userName, List<GrantedAuthority> authorities) {
        this.userName = userName;
        this.authorities = authorities;
    }

    public static UserDetails of(Member user) {
        List<GrantedAuthority> authorities = Arrays.stream(UserRole.values())
                .map(userRole -> new SimpleGrantedAuthority(
                        userRole.getRole()))
                .collect(Collectors.toList());

        return new MemberDetailsImpl(String.valueOf(user.getId()), authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return true;
    }
}
