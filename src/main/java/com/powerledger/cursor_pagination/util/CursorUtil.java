package com.powerledger.cursor_pagination.util;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

public class CursorUtil {

    public static String encode(Instant createdAt) {
        return Base64.getUrlEncoder().encodeToString(createdAt.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static Instant decode(String cursor) {
        try {
            String decoded = new String(Base64.getUrlDecoder().decode(cursor), StandardCharsets.UTF_8);
            return Instant.parse(decoded);
        } catch (Exception e) {
            return null;
        }
    }
}