package com.powerledger.cursor_pagination.controller;

import com.powerledger.cursor_pagination.dto.CursorPageResponse;
import com.powerledger.cursor_pagination.dto.SessionDto;
import com.powerledger.cursor_pagination.service.SessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public CursorPageResponse<SessionDto> getSessions(
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return sessionService.getSessions(cursor, limit);
    }
}
