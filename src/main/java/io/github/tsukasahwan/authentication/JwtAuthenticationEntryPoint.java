package io.github.tsukasahwan.authentication;

import io.github.tsukasahwan.jwt.exception.AccessTokenBlacklistedException;
import io.github.tsukasahwan.jwt.exception.ExpiredJwtException;
import io.github.tsukasahwan.jwt.exception.InvalidTokenException;
import io.github.tsukasahwan.jwt.exception.RefreshTokenRevokedException;
import io.github.tsukasahwan.jwt.util.WebUtils;
import io.github.tsukasahwan.util.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证失败处理类
 *
 * @author Teamo
 * @since 2025/4/11
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof UsernameNotFoundException) {
            // 用户不存在
            WebUtils.renderJson(response, Result.of(HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误"));
        } else if (authException instanceof BadCredentialsException) {
            // 用户名或密码错误
            WebUtils.renderJson(response, Result.of(HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误"));
        } else if (authException instanceof InvalidTokenException) {
            // 无效的token
            WebUtils.renderJson(response, Result.of(HttpServletResponse.SC_UNAUTHORIZED, "无效令牌"));
        } else if (authException instanceof AccessTokenBlacklistedException) {
            // 访问令牌已列入黑名单
            WebUtils.renderJson(response, Result.of(HttpServletResponse.SC_UNAUTHORIZED, "访问令牌已列入黑名单"));
        } else if (authException instanceof RefreshTokenRevokedException) {
            // 刷新令牌已撤销
            WebUtils.renderJson(response, Result.of(HttpServletResponse.SC_UNAUTHORIZED, "刷新令牌已撤销"));
        } else if (authException instanceof ExpiredJwtException) {
            // 令牌已过期
            WebUtils.renderJson(response, Result.of(HttpServletResponse.SC_UNAUTHORIZED, "token已过期，请重新登录"));
        } else {
            // 其他异常
            log.error("认证失败", authException);
            WebUtils.renderJson(response, Result.of(HttpServletResponse.SC_UNAUTHORIZED, "认证失败"));
        }
    }
}
