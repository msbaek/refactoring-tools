package com.example.refactorings.splitphase;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AuditManagerTest {
    @Test
    void addRecord() {
        int maxAuditEntries = 0;
        String directoryName = "~/Downloads/";
        AuditManager auditManager = new AuditManager(maxAuditEntries, directoryName);
        String visitorName = "visitorName";
        LocalDateTime timeOfVisit = LocalDateTime.of(2022, 3, 25, 14, 0);
        auditManager.addRecord(visitorName, timeOfVisit);
    }
}