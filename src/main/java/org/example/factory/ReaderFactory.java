package org.example.factory;

import org.example.reader.CSVReader;
import org.example.interfaces.Reader;
import org.example.reader.DBReader;
import org.example.utility.FileExtension;

public class ReaderFactory {
    public static Reader createReader(FileExtension fileExtension){
        switch(fileExtension){
            case CSV ->{
                return new CSVReader();
            }
            case PDF -> {
                //to implement
            }
            case DOCX -> {
               //to implement
            }
            case SQL ->{
                return new DBReader();
            }
        }
        return null;
    }
}
