package com.example.messagingstompwebsocket.security;

import com.example.messagingstompwebsocket.model.Roles;
import com.example.messagingstompwebsocket.model.User;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ToString
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private String id;

    private String username;

    private String password;

    private boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    private String key;

    private long credits;

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities;
        if (user.isAdmin())
            authorities = Collections.singletonList(new SimpleGrantedAuthority(Roles.ROLE_ADMIN.name()));
        else
            authorities = Collections.singletonList(new SimpleGrantedAuthority(Roles.ROLE_USER.name()));

        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), user.isEnabled(), authorities, user.getKey(), user.getCredits());
    }

    public String getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public long getCredits() {
        return this.credits;
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
        return this.enabled;
    }
}
