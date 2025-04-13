package io.github.tsukasahwan.service;


import io.github.tsukasahwan.jwt.core.Jwt;

/**
 * @author Teamo
 * @since 2025/4/11
 */
public interface UserService {

    Jwt login(String username, String password);

    Jwt refresh();

    void logout();

}
