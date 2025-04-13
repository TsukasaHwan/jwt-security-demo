package io.github.tsukasahwan.util;

import io.github.tsukasahwan.jwt.util.RSAUtils;

import java.io.IOException;

/**
 * @author Teamo
 * @since 2025/4/11
 */
public class GenRSA {

    public static void main(String[] args) throws IOException {
        String projectRoot = System.getProperty("user.dir");
        String resourcesPath = projectRoot + "/src/main/resources";
        RSAUtils.create(2048).genKeyPair(resourcesPath);
    }
}
