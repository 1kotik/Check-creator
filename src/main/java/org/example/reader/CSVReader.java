package org.example.reader;

import org.example.dto.CustomerInfo;
import org.example.dto.Product;
import org.example.interfaces.Reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements Reader {
    List<String[]> productStock;
    List<String[]> discountCards;

    public CustomerInfo read(String[] path, CustomerInfo customerInfo) throws IOException {
        productStock = readCSVFile(path[0]);
        discountCards = readCSVFile(path[1]);
        customerInfo = fillProductInfo(customerInfo);
        customerInfo = fillDiscountCardInfo(customerInfo);
        return customerInfo;
    }

    public List<String[]> readCSVFile(String path) throws IOException {
        String line;
        List<String[]> destination = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(";");
                destination.add(lines);
            }
            reader.close();
        return destination;
    }

    public CustomerInfo fillProductInfo(CustomerInfo customerInfo) throws IllegalArgumentException {
        int quantityInStock;
        String[] line;
        for (Product product : customerInfo.getProducts()) {
            line = productStock.get(product.getId());
            quantityInStock = Integer.parseInt(line[3]);
            if (quantityInStock < product.getQuantity()) {
                throw new IllegalArgumentException("ILLEGAL QUANTITY");
            }

            product.setName(line[1]);
            product.setPrice(Double.parseDouble(line[2].replace(',', '.')));
            product.setWholesale(line[4].equals("true"));
        }

        return customerInfo;
    }

    public CustomerInfo fillDiscountCardInfo(CustomerInfo customerInfo) {
        if (!customerInfo.getDiscountCard().getCardNumber().isEmpty()) {
            for (int i = 1; i < discountCards.size(); i++) {
                if (discountCards.get(i)[1].equals(customerInfo.getDiscountCard().getCardNumber())) {
                    customerInfo.getDiscountCard()
                            .setDiscountAmount(Integer.parseInt(discountCards.get(i)[2]));
                    return customerInfo;
                }
            }
            customerInfo.getDiscountCard().setDiscountAmount(2);
        }
        return customerInfo;
    }

}
