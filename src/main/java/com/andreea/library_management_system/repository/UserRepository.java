package com.andreea.library_management_system.repository;

import com.andreea.library_management_system.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public User getUserByUsername(String username) {
        String selectUserSql = "SELECT * FROM users WHERE username = ?";

        User user = null;
        Connection connection = DatabaseManager.getInstance().connect();

        try (PreparedStatement userStmt = connection.prepareStatement(selectUserSql)) {
            userStmt.setString(1, username);
            try (ResultSet result = userStmt.executeQuery()) {
                if (result.next()) {
                    user = new User();
                    user.setId(result.getInt("id"));
                    user.setUsername(result.getString("username"));
                    user.setPassword(result.getString("password"));
                    user.setEmail(result.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
