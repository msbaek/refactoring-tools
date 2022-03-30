package com.example.refactorings.splitphase;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

public class CountOrder {
    private record Order(String status) {
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0)
                throw new RuntimeException("파일명을 입력하세요");
            String filename = args[args.length - 1];
            File input = Paths.get(filename).toFile();
            ObjectMapper mapper = new ObjectMapper();
            Order[] orders = mapper.readValue(input, Order[].class);
            if (Arrays.asList(args).contains("-r")) {
                System.out.println(Arrays.stream(orders)
                                         .filter(o -> "ready".equals(o.status()))
                                         .count());
            }
            else
                System.out.println(orders.length);
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}