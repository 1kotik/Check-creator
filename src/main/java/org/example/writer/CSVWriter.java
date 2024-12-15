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
        }catch (IOException e){
            System.out.println("DESTINATION FILE ERROR");
        }
    }
}
