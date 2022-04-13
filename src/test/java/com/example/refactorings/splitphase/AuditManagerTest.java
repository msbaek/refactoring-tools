package com.example.refactorings.splitphase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditManagerTest {
    @Mock
    private FileSystem fileSystem;

    @Test
    void addRecord_with_normal_case() {
        String directoryName = "audits";
        String[] files = {
                directoryName + "\\audit_1.txt",
                directoryName + "\\audit_2.txt"
        };
        String[] lines = {
                "Peter; 2019-04-06T16:30:00",
                "Jane; 2019-04-06T16:40:00",
                "Jack; 2019-04-06T17:00:00"
        };
        LocalDateTime dateTime = LocalDateTime.of(2022, 3, 25, 14, 0);
        String visitorName = "Alice";

        when(fileSystem.getFiles(directoryName))
                .thenReturn(files);
        when(fileSystem.readAllLines(directoryName + "\\" + "audit_2.txt"))
                .thenReturn(lines);

        AuditManager auditManager = new AuditManager(3, directoryName, fileSystem);
        FileUpdate update = auditManager.addRecord(visitorName, dateTime);
        assertThat(update.fileName()).isEqualTo("audits:audit_" + (files.length + 1) + ".txt");
        assertThat(update.newContent()).isEqualTo(visitorName + ";" + dateTime);
    }
}