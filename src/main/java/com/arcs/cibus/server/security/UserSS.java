package com.arcs.cibus.server.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arcs.cibus.server.domain.enums.Profile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserSS implements UserDetails
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    private String pass;
    private Profile profile;

    public Long getId()
    {
        return id;
    }

    public Profile getProfile()
    {
        return profile;
    }

    //Perfis do usuário
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return pass;
    }

    @Override
    public String getUsername()
    {
        return login;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

}
