package com.powerledger.cursor_pagination.service;

import com.powerledger.cursor_pagination.dto.CursorPageResponse;
import com.powerledger.cursor_pagination.dto.SessionDto;
import com.powerledger.cursor_pagination.model.Session;
import com.powerledger.cursor_pagination.repository.SessionRepository;
import com.powerledger.cursor_pagination.util.CursorUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public CursorPageResponse<SessionDto> getSessions(String cursor, int limit) {

        Instant createdAtCursor = CursorUtil.decode(cursor);

        List<Session> sessions;

        if (createdAtCursor == null) {
            sessions = sessionRepository.findAllByOrderByCreatedAtAsc(PageRequest.of(0, limit));
        } else {
            // Fetch sessions greater than or equal to cursor
            sessions = sessionRepository.findByCreatedAtAfterOrderByCreatedAtAsc(createdAtCursor, PageRequest.of(0, limit));
        }

        // Encode last item as nextCursor
        String nextCursor = sessions.isEmpty()
                ? null
                : CursorUtil.encode(sessions.get(sessions.size() - 1).getCreatedAt());

        List<SessionDto> dtos = sessions.stream()
                .map(s -> new SessionDto(s.getId(), s.getTitle(), s.getCreatedAt()))
                .toList();

        return new CursorPageResponse<>(dtos, nextCursor);
    }
}
