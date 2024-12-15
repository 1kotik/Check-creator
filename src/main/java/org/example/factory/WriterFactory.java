package org.example.factory;


import org.example.reader.CommandLineReader;
import org.example.writer.CSVWriter;
import org.example.interfaces.Writer;
import org.example.utility.FileExtension;

public class WriterFactory {
    public static Writer createWriter(FileExtension fileExtension) {
        switch (fileExtension) {
            case CSV -> {
                return new CSVWriter();
            }
            case PDF -> {
                //to implement
            }
            case DOCX -> {
                //to implement
            }

        }
        return null;
    }
}
