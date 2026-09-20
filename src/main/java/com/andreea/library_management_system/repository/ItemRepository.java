package com.andreea.library_management_system.repository;

import com.andreea.library_management_system.entity.Item;

import java.sql.*;

public class ItemRepository {

    public int saveItem(Item item) {
        String insertItemSql = "INSERT INTO items (title, publish_year, available) VALUES (?,?,?)";

        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement itemStmt = connection.prepareStatement(insertItemSql, Statement.RETURN_GENERATED_KEYS)) {
            itemStmt.setString(1, item.getTitle());
            itemStmt.setInt(2, item.getPublishYear());
            itemStmt.setBoolean(3, item.getAvailable());

            itemStmt.executeUpdate();
            try (ResultSet generatedKeys = itemStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateItem(Item item, int id) {
        String updateItemSql = "UPDATE items SET title = ?, publish_year = ?, available = ? WHERE id = ?";

        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement itemStmt = connection.prepareStatement(updateItemSql)) {
            itemStmt.setString(1, item.getTitle());
            itemStmt.setInt(2, item.getPublishYear());
            itemStmt.setBoolean(3, item.getAvailable());
            itemStmt.setInt(4, id);

            itemStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id) {
        String deleteItemSql = "DELETE FROM items WHERE id = ?";

        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement itemStmt = connection.prepareStatement(deleteItemSql)) {
            itemStmt.setInt(1, id);

            itemStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
