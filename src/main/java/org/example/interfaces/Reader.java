package org.example.interfaces;

import org.example.dto.CustomerInfo;

import java.io.IOException;

public interface Reader {
    CustomerInfo read(CustomerInfo customerInfo) throws Exception;
}
