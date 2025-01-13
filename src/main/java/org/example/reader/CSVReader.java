package org.example.reader;

import org.example.dto.CustomerInfo;
import org.example.dto.Product;
import org.example.interfaces.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements Reader {
    final static String[] path = {"./src/main/resources/products.csv",
            "./src/main/resources/discountCards.csv" };
    List<String[]> productStock;
    List<String[]> discountCards;

    public CustomerInfo read(CustomerInfo customerInfo) throws Exception {
        productStock = readCSVFile(customerInfo.getFileInfo().getSourcePath());
        discountCards = readCSVFile(path[1]);
        fillProductInfo(customerInfo);
        fillDiscountCardInfo(customerInfo);
        return customerInfo;
    }

    private List<String[]> readCSVFile(String path) throws Exception {
        String line;
        List<String[]> destination = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(";");
                destination.add(lines);
            }
            reader.close();
        }catch (Exception e) {
            throw new Exception("BAD REQUEST");
        }
        return destination;
    }

    private void fillProductInfo(CustomerInfo customerInfo) throws IllegalArgumentException {
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

    }

    private void fillDiscountCardInfo(CustomerInfo customerInfo) {
        if (!customerInfo.getDiscountCard().getCardNumber().isEmpty()) {
            for (int i = 1; i < discountCards.size(); i++) {
                if (discountCards.get(i)[1].equals(customerInfo.getDiscountCard().getCardNumber())) {
                    customerInfo.getDiscountCard()
                            .setDiscountAmount(Integer.parseInt(discountCards.get(i)[2]));
                    return;
                }
            }
            customerInfo.getDiscountCard().setDiscountAmount(2);
        }
    }

}
