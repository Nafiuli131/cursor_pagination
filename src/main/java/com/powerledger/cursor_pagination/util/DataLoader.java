package com.powerledger.cursor_pagination.util;

import com.powerledger.cursor_pagination.model.Session;
import com.powerledger.cursor_pagination.repository.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final SessionRepository sessionRepository;

    public DataLoader(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void run(String... args) {
        if (sessionRepository.count() > 0) return;
        List<Session> sessions = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            Session s = new Session();
            s.setTitle("Session " + i);
            s.setCreatedAt(Instant.now().plusSeconds(i * 60));
            sessions.add(s);
        }
        sessionRepository.saveAll(sessions);
    }
}
