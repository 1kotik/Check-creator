package org.example.utility;

import org.example.dto.CustomerInfo;
import org.example.dto.DiscountCard;
import org.example.dto.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckComputer {
    static double totalPrice;
    static double totalDiscount;

    public static List<String[]> computeCheckInfo(CustomerInfo customerInfo) throws Exception {
        List<String[]> checkInfo = new ArrayList<>();
        writeTime(checkInfo);
        writeProductList(customerInfo, checkInfo);
        writeDiscountCard(customerInfo, checkInfo);
        writeTotal(customerInfo, checkInfo);
        return checkInfo;
    }

    private static void writeTime(List<String[]> checkInfo) {
        checkInfo.add(new String[]{"Date", "Time"});
        checkInfo.add(new String[]{new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()),
                new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())});
        checkInfo.add(new String[]{});
    }

    private static void writeProductList(CustomerInfo customerInfo, List<String[]> checkInfo) {
        checkInfo.add(new String[]{"QTY", "DESCRIPTION", "PRICE", "DISCOUNT", "TOTAL"});
        for (Product product : customerInfo.getProducts()) {
            int discount;
            if (product.getWholesale()) {
                discount = 10;
            } else {
                discount = customerInfo.getDiscountCard().getDiscountAmount();
            }
            checkInfo.add(new String[]{String.valueOf(product.getQuantity()), product.getName(),
                    String.format("%.2f$", product.getPrice()),
                    String.format("%.2f$", product.getPrice() * product.getQuantity()),
                    String.format("%.2f$", product.getPrice() * product.getQuantity() * discount / 100)});
            totalPrice += product.getPrice() * product.getQuantity();
            totalDiscount += product.getPrice() * product.getQuantity() * discount / 100;
        }
        checkInfo.add(new String[]{});
    }

    private static void writeDiscountCard(CustomerInfo customerInfo, List<String[]> checkInfo) {
        if (customerInfo.getDiscountCard().getDiscountAmount() != 0) {
            checkInfo.add(new String[]{"DISCOUNT CARD", "DISCOUNT PERCENTAGE"});
            checkInfo.add(new String[]{customerInfo.getDiscountCard().getCardNumber(),
                    String.format("%d%%", customerInfo.getDiscountCard().getDiscountAmount())});
            checkInfo.add(new String[]{});
        }
    }

    private static void writeTotal(CustomerInfo customerInfo, List<String[]> checkInfo) throws Exception {
        if(totalPrice - totalDiscount > customerInfo.getBalanceDebitCard()){
            throw new Exception("NOT ENOUGH MONEY");
        }

        checkInfo.add(new String[]{"TOTAL PRICE", "TOTAL DISCOUNT", "TOTAL WITH DISCOUNT"});
        checkInfo.add(new String[]{String.format("%.2f$", totalPrice), String.format("%.2f$", totalDiscount),
                String.format("%.2f", totalPrice - totalDiscount)});
    }
}
