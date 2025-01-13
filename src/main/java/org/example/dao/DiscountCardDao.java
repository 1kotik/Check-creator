package org.example.dao;

import org.example.dto.DiscountCard;
import org.example.utility.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountCardDao {
    private static String SELECT_QUERY = "select * from discount_cards where card_number = ?";

    public List<DiscountCard> getDiscountCards() {
        List<DiscountCard> discountCards = new ArrayList<>();
        DiscountCard discountCard = new DiscountCard();
        try {
            Connection connection = DBConnector.getConnnection();
            PreparedStatement statement = connection.prepareStatement("select * from discount_cards");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                discountCard.setId(resultSet.getInt("card_id"));
                discountCard.setCardNumber(resultSet.getString("card_number"));
                discountCard.setDiscountAmount(resultSet.getInt("discount_amount"));
                discountCards.add(discountCard);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountCards;
    }

    public DiscountCard getDiscountCardByCardNumber(String cardNumber) {
        DiscountCard discountCard = new DiscountCard();
        try {
            Connection connection = DBConnector.getConnnection();
            PreparedStatement statement = connection
                    .prepareStatement(SELECT_QUERY);
            statement.setString(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            discountCard.setId(resultSet.getInt("card_id"));
            discountCard.setCardNumber(resultSet.getString("card_number"));
            discountCard.setDiscountAmount(resultSet.getInt("discount_amount"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountCard;
    }

}
