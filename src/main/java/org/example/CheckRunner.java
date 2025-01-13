package org.example;

import org.example.dto.CustomerInfo;
import org.example.factory.ReaderFactory;
import org.example.factory.WriterFactory;
import org.example.interfaces.Reader;
import org.example.interfaces.Writer;
import org.example.reader.CommandLineReader;
import org.example.utility.CheckComputer;
import org.example.utility.DBConnector;
import org.example.utility.FileExtension;
import org.example.writer.CommandLineWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CheckRunner {
    static Reader reader;
    static Writer writer;


    public static void main(String[] args) throws IOException {
        CustomerInfo customerInfo = new CustomerInfo();
        List <String[]> checkInfo;
        try {
            configure();
            DBConnector.setDbInfo(CommandLineReader.readDbInfo(args));
            customerInfo = CommandLineReader.readArguments(args);
            customerInfo = reader.read(customerInfo);
            checkInfo = CheckComputer.computeCheckInfo(customerInfo);
            writer.write(customerInfo.getFileInfo().getDestPath(), checkInfo);
        }catch(Exception e) {
             writer.write(customerInfo.getFileInfo().getDestPath(),
                     Arrays.asList(new String[] {"ERROR"}, new String[] {e.getMessage()}));
        }
    }

    static void configure() {
        reader = ReaderFactory.createReader(FileExtension.SQL);
        writer = WriterFactory.createWriter(FileExtension.CSV);

    }
}
