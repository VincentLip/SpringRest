package com.example.tp_spring_security.entity;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


@ToString
public class UserDetailsImpl implements UserDetails {


    private int id;

    private String username;

    private String password;

    public UserDetailsImpl(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(Math.toIntExact(user.getId()),user.getUsername(), user.getPassword());
    }

    public int getId() {
        return id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return username;
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
