package com.example.refactorings.splitphase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

record Sorted(int index, FileContent fileContent) {
}

class Path {
    public static String combine(String directoryName, String fileName) {
        return directoryName + java.io.File.pathSeparator + fileName;
    }
}

record FileContent(String fileName, String[] lines) {
}

record FileUpdate(String fileName, String newContent) {
}

public class AuditManager {
    private final int maxEntriesPerFile;
    private final String directoryName;

    public AuditManager(int maxEntriesPerFile, String directoryName) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
    }

    public FileUpdate addRecord(FileContent[] files, String visitorName, LocalDateTime timeOfVisit) {
        Sorted[] sorted = sortByIndex(files);

        String newRecord = visitorName + ';' + timeOfVisit;

        if (sorted.length == 0) {
            String newFile = Path.combine(directoryName, "audit_1.txt");
            return new FileUpdate(newFile, newRecord);
        }

        int currentFileIndex = sorted[sorted.length - 1].index();
        FileContent currentFilePath = sorted[sorted.length - 1].fileContent();
        List<String> lines = new ArrayList<>(Arrays.asList(currentFilePath.lines()));

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\n", lines);
            return new FileUpdate(currentFilePath.fileName(), newContent);
        }
        else {
            int newIndex = currentFileIndex + 1;
            String newName = "audit_" + newIndex + ".txt";
            String newFile = Path.combine(directoryName, newName);
            return new FileUpdate(newFile, newRecord);
        }
    }

    private Sorted[] sortByIndex(FileContent[] filePaths) {
        AtomicInteger idx = new AtomicInteger(1);
        return Arrays.stream(filePaths)
                     .map(s -> new Sorted(idx.getAndIncrement(), s))
                     .toArray(Sorted[]::new);
    }
}