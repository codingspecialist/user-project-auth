package com.getinthere.evaluate.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class LoginUserDetails implements UserDetails {
    private final SessionUser sessionUser;

    public LoginUserDetails(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> sessionUser.getUser().getRole());

        return authorities;
    }

    @Override
    public String getPassword() {
        return sessionUser.getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return sessionUser.getUser().getUsername();
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
