package org.example.reader;

import org.example.dao.DiscountCardDao;
import org.example.dao.ProductDao;
import org.example.dto.CustomerInfo;
import org.example.dto.DiscountCard;
import org.example.dto.Product;
import org.example.interfaces.Reader;

public class DBReader implements Reader {
    ProductDao productDao = new ProductDao();
    DiscountCardDao discountCardDao = new DiscountCardDao();

    public CustomerInfo read(CustomerInfo customerInfo) throws Exception {
        fillProductInfo(customerInfo);
        fillDiscountCardInfo(customerInfo);
        return customerInfo;
    }

    public void fillProductInfo(CustomerInfo customerInfo) {
        for (Product product : customerInfo.getProducts()) {
            Product dbProduct = productDao.getProductById(product.getId());
            if (dbProduct != null) {
                if (dbProduct.getQuantity() < product.getQuantity()) {
                    throw new IllegalArgumentException("ILLEGAL QUANTITY");
                }
                product.setName(dbProduct.getName());
                product.setPrice(dbProduct.getPrice());
                product.setWholesale(dbProduct.getWholesale());
            }
        }
    }

    public void fillDiscountCardInfo(CustomerInfo customerInfo) {
        DiscountCard dbDiscountCard = discountCardDao.getDiscountCardByCardNumber(customerInfo.getDiscountCard().getCardNumber());
        if (dbDiscountCard != null) {
            customerInfo.getDiscountCard().setDiscountAmount(dbDiscountCard.getDiscountAmount());
        } else {
            customerInfo.getDiscountCard().setDiscountAmount(2);
        }
    }
}
