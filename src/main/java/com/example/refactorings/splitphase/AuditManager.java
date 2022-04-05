package com.example.refactorings.splitphase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

record Sorted(int index, String path) {
}

record FileContent(String FileName,String[] Lines) {
}

record FileUpdate(String fileName, String newContent) {
}

class Path {
    public static String combine(String directoryName, String fileName) {
        return directoryName + java.io.File.pathSeparator + fileName;
    }
}

interface FileSystem {
    String[] getFiles(String directoryName);
    void writeAllText(String filePath, String content);
    List<String> readAllLines(String filePath);
}

public class AuditManager {
    private final int maxEntriesPerFile;
    private final String directoryName;
    private final FileSystem fileSystem;

    public AuditManager(int maxEntriesPerFile, String directoryName, FileSystem fileSystem) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
        this.fileSystem = fileSystem;
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) {
        String[] filePaths = fileSystem.getFiles(directoryName);
        Sorted[] sorted = sortByIndex(filePaths);

        String newRecord = visitorName + ';' + timeOfVisit;

        if (sorted.length == 0) {
            String newFile = Path.combine(directoryName, "audit_1.txt");
            fileSystem.writeAllText(newFile, newRecord);
            return;
        }

        int currentFileIndex = sorted[sorted.length - 1].index();
        String currentFilePath = sorted[sorted.length - 1].path();
        List<String> lines = new ArrayList<>(fileSystem.readAllLines(currentFilePath));

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\n", lines);
            fileSystem.writeAllText(currentFilePath, newContent);
        }
        else {
            int newIndex = currentFileIndex + 1;
            String newName = "audit_" + newIndex + ".txt";
            String newFile = Path.combine(directoryName, newName);
            fileSystem.writeAllText(newFile, newRecord);
        }
    }

    private Sorted[] sortByIndex(String[] filePaths) {
        AtomicInteger idx = new AtomicInteger(1);
        return Arrays.stream(filePaths)
                     .map(s -> new Sorted(idx.getAndIncrement(), s))
                     .toArray(Sorted[]::new);
    }
}