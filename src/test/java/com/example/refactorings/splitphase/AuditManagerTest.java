package com.example.refactorings.splitphase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AuditManagerTest {
    @Test
    void addRecord_with_normal_case() {
        String directoryName = "audits";
        FileContent[] files = new FileContent[]{
                new FileContent(directoryName + "\\audit_1.txt", new String[0]),
                new FileContent(directoryName + "\\audit_2.txt", new String[]{
                        "Peter; 2019-04-06T16:30:00",
                        "Jane; 2019-04-06T16:40:00",
                        "Jack; 2019-04-06T17:00:00"
                })
        };

        LocalDateTime dateTime = LocalDateTime.of(2022, 3, 25, 14, 0);
        String visitorName = "Alice";

        AuditManager auditManager = new AuditManager(3, directoryName);
        FileUpdate update = auditManager.addRecord(files, visitorName, dateTime);
        assertThat(update.fileName()).isEqualTo("audits:audit_" + (files.length + 1) + ".txt");
        assertThat(update.newContent()).isEqualTo(visitorName + ";" + dateTime);
    }
}