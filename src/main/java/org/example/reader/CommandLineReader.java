package org.example.reader;

import org.example.dto.CustomerInfo;
import org.example.dto.DiscountCard;
import org.example.dto.FileInfo;
import org.example.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class CommandLineReader {
    public static CustomerInfo readArguments(String[] args) {
        List<Product> products = new ArrayList<>();
        DiscountCard discountCard = new DiscountCard();
        double balanceDebitCard = 0.0;
        CustomerInfo customerInfo = new CustomerInfo();
        FileInfo fileInfo = new FileInfo();

        for (String arg : args) {
            if (arg.contains("-")) {
                String[] parts = arg.split("-");
                products.add(new Product(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            } else if (arg.contains("=")) {
                String[] parts = arg.split("=");
                String parameter = parts[0];
                String value = parts[1];
                if (parameter.equals("discountCard")) {
                    discountCard.setCardNumber(value);
                } else if (parameter.equals("balanceDebitCard")) {
                    balanceDebitCard = Double.parseDouble(value);
                } else if (parameter.equals("pathToFile")) {
                    fileInfo.setSourcePath(value);
                } else if (parameter.equals("saveToFile")) {
                    fileInfo.setDestPath(value);
                }
            }
        }

        customerInfo.setProducts(preprocessProductList(products));
        customerInfo.setDiscountCard(discountCard);
        customerInfo.setBalanceDebitCard(balanceDebitCard);
        customerInfo.setFileInfo(fileInfo);

        return customerInfo;
    }

    public static List<Product> preprocessProductList(List<Product> productList) {
        List<Product> filteredProductList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            for (int j = i + 1; j < productList.size(); j++) {
                if (productList.get(i).getId() == productList.get(j).getId()) {
                    productList.get(i).increaseQuantity(productList.get(j).getQuantity());
                    productList.remove(j);
                }
            }
            filteredProductList.add(productList.get(i));
        }

        return filteredProductList;
    }
}
