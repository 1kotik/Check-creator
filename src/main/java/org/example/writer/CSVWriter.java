package org.example.writer;

import org.example.dto.CustomerInfo;
import org.example.interfaces.Writer;

import java.io.*;
import java.util.List;

public class CSVWriter implements Writer {
    public void write(String path, List<String[]> info) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (String[] infoItem : info) {
                String stringWithDelimiter = String.join(";", infoItem);
                writer.write(stringWithDelimiter);
                writer.newLine();
            }
            writer.close();
            CommandLineWriter.write(info);
        }catch (Exception e){
            System.out.println("BAD REQUEST");
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/result.csv"));
            writer.write("ERROR");
            writer.newLine();
            writer.write("BAD REQUEST");
            writer.close();
        }
    }
}
