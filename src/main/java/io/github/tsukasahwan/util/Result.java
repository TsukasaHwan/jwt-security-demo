package io.github.tsukasahwan.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Teamo
 * @since 2025/4/11
 */
public class Result {

    public static Map<String, Object> of(int code, String message) {
        return of(code, null, message);
    }

    public static Map<String, Object> of(int code, Object data, String message) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", code);
        result.put("data", data);
        result.put("message", message);
        return result;
    }
}
