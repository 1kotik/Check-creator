package org.example.interfaces;

import org.example.dto.CustomerInfo;

import java.io.IOException;
import java.util.List;

public interface Writer {
    void write(String path, List<String[]> info) throws IOException;
}
