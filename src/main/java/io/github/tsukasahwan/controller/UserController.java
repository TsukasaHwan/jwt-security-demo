package io.github.tsukasahwan.controller;

import io.github.tsukasahwan.jwt.annotation.RefreshTokenApi;
import io.github.tsukasahwan.jwt.core.Jwt;
import io.github.tsukasahwan.jwt.util.JwtUtils;
import io.github.tsukasahwan.service.UserService;
import io.github.tsukasahwan.service.impl.UserDetailServiceImpl;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Teamo
 * @since 2025/4/11
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PermitAll
    @GetMapping("/users/login")
    public ResponseEntity<Jwt> login(@RequestParam String username,
                                     @RequestParam String password) {
        return ResponseEntity.ok(userService.login(username, password));
    }

    @RefreshTokenApi
    @GetMapping("/users/refresh-token")
    public ResponseEntity<Jwt> refreshToken() {
        return ResponseEntity.ok(userService.refresh());
    }

    @GetMapping("/users/logout")
    public ResponseEntity<Void> logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String accessToken = (String) authentication.getCredentials();
        String accessToken = JwtUtils.getTokenValue();
        UserDetailServiceImpl.User principal = (UserDetailServiceImpl.User) authentication.getPrincipal();
        log.info("访问令牌: {}", accessToken);
        log.info("{} 访问成功", principal.getUsername());
        return ResponseEntity.ok().build();
    }

}
