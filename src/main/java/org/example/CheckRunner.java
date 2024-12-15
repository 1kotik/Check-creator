package org.example;

import org.example.dto.CustomerInfo;
import org.example.factory.ReaderFactory;
import org.example.factory.WriterFactory;
import org.example.interfaces.Reader;
import org.example.interfaces.Writer;
import org.example.reader.CommandLineReader;
import org.example.utility.CheckComputer;
import org.example.utility.FileExtension;
import org.example.writer.CommandLineWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CheckRunner {
    static Reader reader;
    static Writer writer;
    final static String[] sourceFiles = {"./src/main/resources/products.csv",
                                         "./src/main/resources/discountCards.csv" };

    public static void main(String[] args) throws IOException {
        CustomerInfo customerInfo;
        List <String[]> checkInfo;
        try {
            configure();
            customerInfo = CommandLineReader.readArguments(args);
            customerInfo = reader.read(sourceFiles, customerInfo);
            checkInfo = CheckComputer.computeCheckInfo(customerInfo);
            writer.write("./src/main/resources/result.csv", checkInfo);
            CommandLineWriter.write(checkInfo);
        }catch(Exception e) {
             writer.write("./src/main/resources/result.csv",
                     Arrays.asList(new String[] {"ERROR"}, new String[] {e.getMessage()}));
        }
    }

    static void configure() {
        reader = ReaderFactory.createReader(FileExtension.CSV);
        writer = WriterFactory.createWriter(FileExtension.CSV);
    }
}
