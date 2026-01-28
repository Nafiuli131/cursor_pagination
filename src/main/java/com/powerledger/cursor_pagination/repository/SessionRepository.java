package com.powerledger.cursor_pagination.repository;

import com.powerledger.cursor_pagination.model.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session,Long> {
    // First page (no cursor)
    List<Session> findAllByOrderByCreatedAtAsc(Pageable pageable);

    // Next pages (cursor-based)
    List<Session> findByCreatedAtAfterOrderByCreatedAtAsc(Instant cursor, Pageable pageable);
}
