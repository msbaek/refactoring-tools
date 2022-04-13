package com.example.refactorings.splitphase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

record Sorted(int index, String path) {
}

class Path {
    public static String combine(String directoryName, String fileName) {
        return directoryName + java.io.File.pathSeparator + fileName;
    }
}

interface FileSystem {
    String[] getFiles(String directoryName);
    public String [] readAllLines(String filePath);
}

record FileContent(String FileName,String[] Lines) {
}

record FileUpdate(String fileName, String newContent) {
}

public class AuditManager {
    private final int maxEntriesPerFile;
    private final String directoryName;
    private FileSystem fileSystem;

    public AuditManager(int maxEntriesPerFile, String directoryName, FileSystem fileSystem) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
        this.fileSystem = fileSystem;
    }

    public FileUpdate addRecord(String visitorName, LocalDateTime timeOfVisit) {
        String[] filePaths = fileSystem.getFiles(directoryName);
        Sorted[] sorted = sortByIndex(filePaths);

        String newRecord = visitorName + ';' + timeOfVisit;

        if (sorted.length == 0) {
            String newFile = Path.combine(directoryName, "audit_1.txt");
            return new FileUpdate(newFile, newRecord);
        }

        int currentFileIndex = sorted[sorted.length - 1].index();
        String currentFilePath = sorted[sorted.length - 1].path();
        List<String> lines = new ArrayList<>(Arrays.asList(fileSystem.readAllLines(currentFilePath)));

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\n", lines);
            return new FileUpdate(currentFilePath, newContent);
        }
        else {
            int newIndex = currentFileIndex + 1;
            String newName = "audit_" + newIndex + ".txt";
            String newFile = Path.combine(directoryName, newName);
            return new FileUpdate(newFile, newRecord);
        }
    }

    private Sorted[] sortByIndex(String[] filePaths) {
        AtomicInteger idx = new AtomicInteger(1);
        return Arrays.stream(filePaths)
                     .map(s -> new Sorted(idx.getAndIncrement(), s))
                     .toArray(Sorted[]::new);
    }
}