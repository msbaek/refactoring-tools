package com.example.refactorings.splitphase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Directory {
    public static String[] getFiles(String directoryName) {
        throw new IllegalStateException("Directory#getFiles is not implemented yet");
    }
}

class File {
    public static void writeAllText(String newFile, String newRecord) {
        throw new IllegalStateException("File#writeAllText is not implemented yet");
    }

    public static String [] readAllLines(String currentFilePath) {
        throw new IllegalStateException("File#readAllLines is not implemented yet");
    }
}

record Sorted(int index, String path) {
}

class Path {
    public static String combine(String directoryName, String fileName) {
        return directoryName + java.io.File.pathSeparator + fileName;
    }
}

public class AuditManager {
    private final int maxEntriesPerFile;
    private final String directoryName;

    public AuditManager(int maxEntriesPerFile, String directoryName) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) {
        String[] filePaths = Directory.getFiles(directoryName);
        Sorted[] sorted = sortByIndex(filePaths);

        String newRecord = visitorName + ';' + timeOfVisit;

        if (sorted.length == 0) {
            String newFile = Path.combine(directoryName, "audit_1.txt");
            File.writeAllText(newFile, newRecord);
            return;
        }

        int currentFileIndex = sorted[sorted.length - 1].index();
        String currentFilePath = sorted[sorted.length - 1].path();
        List<String> lines = new ArrayList<>(Arrays.asList(File.readAllLines(currentFilePath)));

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\n", lines);
            File.writeAllText(currentFilePath, newContent);
        }
        else {
            int newIndex = currentFileIndex + 1;
            String newName = "audit_" + newIndex + ".txt";
            String newFile = Path.combine(directoryName, newName);
            File.writeAllText(newFile, newRecord);
        }
    }

    private Sorted[] sortByIndex(String[] filePaths) {
        AtomicInteger idx = new AtomicInteger(1);
        return Arrays.stream(filePaths)
                     .map(s -> new Sorted(idx.getAndIncrement(), s))
                     .toArray(Sorted[]::new);
    }
}