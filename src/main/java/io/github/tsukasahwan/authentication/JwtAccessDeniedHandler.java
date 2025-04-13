package io.github.tsukasahwan.authentication;

import io.github.tsukasahwan.util.Result;
import io.github.tsukasahwan.jwt.util.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Teamo
 * @since 2025/4/11
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 权限不足
        WebUtils.renderJson(response, Result.of(HttpServletResponse.SC_FORBIDDEN, "权限不足，无权操作"));
    }
}
