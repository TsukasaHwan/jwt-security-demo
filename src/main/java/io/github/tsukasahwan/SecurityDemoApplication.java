package io.github.tsukasahwan;

import io.github.tsukasahwan.jwt.EnableJwtSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Teamo
 * @since 2025/4/11
 */
@EnableJwtSecurity
@SpringBootApplication
public class SecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }
}
