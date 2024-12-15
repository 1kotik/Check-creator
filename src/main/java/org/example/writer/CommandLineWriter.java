package org.example.writer;

import java.util.List;

public class CommandLineWriter {
    public static void write(List<String[]> checkInfo) {
        for (String[] info : checkInfo) {
            System.out.println(String.join("\t", info));
        }
    }
}
