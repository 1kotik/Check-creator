package org.example.dao;

import org.example.dto.DiscountCard;
import org.example.dto.Product;
import org.example.utility.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductDao {
    private static String SELECT_QUERY = "select * from products where prod_id = ?";

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        try {
            Connection connection = DBConnector.getConnnection();
            PreparedStatement statement = connection.prepareStatement("select * from products");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                product.setId(resultSet.getInt("prod_id"));
                product.setName(resultSet.getString("prod_name"));
                product.setPrice(resultSet.getDouble("prod_price"));
                product.setQuantity(resultSet.getInt("prod_quantity"));
                product.setWholesale(resultSet.getBoolean("prod_wholesale"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int id) {
        Product product = new Product();
        try {
            Connection connection = DBConnector.getConnnection();
            PreparedStatement statement = connection
                    .prepareStatement(SELECT_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            product.setId(resultSet.getInt("prod_id"));
            product.setName(resultSet.getString("prod_name"));
            product.setPrice(resultSet.getDouble("prod_price"));
            product.setQuantity(resultSet.getInt("prod_quantity"));
            product.setWholesale(resultSet.getBoolean("prod_wholesale"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
