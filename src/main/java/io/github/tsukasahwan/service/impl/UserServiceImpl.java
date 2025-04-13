package io.github.tsukasahwan.service.impl;

import io.github.tsukasahwan.jwt.core.Jwt;
import io.github.tsukasahwan.jwt.security.authentication.JwtAuthenticationManager;
import io.github.tsukasahwan.jwt.util.JwtUtils;
import io.github.tsukasahwan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Teamo
 * @since 2025/4/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtAuthenticationManager jwtAuthenticationManager;

    @Override
    public Jwt login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
        return jwtAuthenticationManager.login(username);
    }

    @Override
    public Jwt refresh() {
        // 获取当前请求token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String refreshToken = (String) authentication.getCredentials();
        String refreshToken = JwtUtils.getTokenValue();
        UserDetailServiceImpl.User principal = (UserDetailServiceImpl.User) authentication.getPrincipal();
        return jwtAuthenticationManager.refresh(principal.getUsername(), refreshToken);
    }

    @Override
    public void logout() {
        // 获取当前请求token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String accessToken = (String) authentication.getCredentials();
        String accessToken = JwtUtils.getTokenValue();
        UserDetailServiceImpl.User principal = (UserDetailServiceImpl.User) authentication.getPrincipal();
        jwtAuthenticationManager.logout(principal.getUsername(), accessToken);
    }
}
