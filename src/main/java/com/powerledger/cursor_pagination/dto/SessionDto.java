package com.powerledger.cursor_pagination.dto;

import java.time.Instant;

public record SessionDto(Long id, String title, Instant createdAt) {

}
