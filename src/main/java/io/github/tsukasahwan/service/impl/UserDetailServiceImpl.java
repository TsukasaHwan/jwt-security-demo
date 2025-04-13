package io.github.tsukasahwan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Teamo
 * @since 2025/4/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final Map<String, User> userMap = Map.of("admin", new User());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMap.get(username);
    }

    public static class User implements UserDetails {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>(2);
            SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER:DEL");
            authorities.add(roleAdmin);
            authorities.add(simpleGrantedAuthority);
            return authorities;
        }

        @Override
        public String getPassword() {
            return "$2a$10$42BbssDi/Ab916u1HuU.8uzp9AWW4AiW.Y0jOGHYKCJ3qVsUUv6Vm";
        }

        @Override
        public String getUsername() {
            return "admin";
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
}
