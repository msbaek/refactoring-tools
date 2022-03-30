package com.example.refactorings.splitphase;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class CountOrder {
    private record Order(String status) {
    }

    public static void main(String[] args) {
        try {
            System.out.println(run(args));
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    static class CommandLine {
    }

    private static long run(String[] args) throws IOException {
        if (args.length == 0)
            throw new RuntimeException("파일명을 입력하세요");
        String filename = args[args.length - 1];
        CommandLine commandLine = new CommandLine();
        return countOrders(commandLine, filename, Arrays.asList(args).contains("-r"));
    }

    private static long countOrders(CommandLine commandLine, String filename, boolean onlyCountReady) throws IOException {
        File input = Paths.get(filename).toFile();
        ObjectMapper mapper = new ObjectMapper();
        Order[] orders = mapper.readValue(input, Order[].class);
        if (onlyCountReady) {
            return Arrays.stream(orders)
                         .filter(o -> "ready".equals(o.status()))
                         .count();
        }
        else
            return orders.length;
    }
}