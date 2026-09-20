package com.andreea.library_management_system.repository;


import com.andreea.library_management_system.entity.Magazine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MagazineRepository {

    private ItemRepository itemRepository;

    public MagazineRepository() {
        this.itemRepository = new ItemRepository();
    }

    public Magazine getMagazineById(int id) {
        String selectMagazineSql = "SELECT items.id, items.title, items.publish_year, items.available, magazines.edition_number, magazines.month_appearance " +
                "FROM items " +
                "JOIN magazines ON items.id = magazines.item_id " +
                "WHERE items.id = ?";

        Connection connection = DatabaseManager.getInstance().connect();
        Magazine magazine = null;
        try (PreparedStatement magazineStmt = connection.prepareStatement(selectMagazineSql)) {
            magazineStmt.setInt(1, id);

            try (ResultSet result = magazineStmt.executeQuery()) {
                if (result.next()) {
                    magazine = new Magazine();
                    magazine.setId(result.getInt("id"));
                    magazine.setTitle(result.getString("title"));
                    magazine.setPublishYear(result.getInt("publish_year"));
                    magazine.setAvailable(result.getBoolean("available"));
                    magazine.setEditionNumber(result.getInt("edition_number"));
                    magazine.setMonthAppearance(result.getInt("month_appearance"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazine;
    }

    public List<Magazine> getAllMagazines() {
        String getAllMagazinesSql = "SELECT items.id, items.title, items.publish_year, items.available, magazines.edition_number, magazines.month_appearance " +
                "FROM items " +
                "JOIN magazines ON items.id = magazines.item_id";

        List<Magazine> magazines = new ArrayList<>();
        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement magazineStmt = connection.prepareStatement(getAllMagazinesSql)) {
            ResultSet result = magazineStmt.executeQuery();

            while (result.next()) {
                Magazine magazine = new Magazine();
                magazine.setId(result.getInt("id"));
                magazine.setTitle(result.getString("title"));
                magazine.setPublishYear(result.getInt("publish_year"));
                magazine.setAvailable(result.getBoolean("available"));
                magazine.setEditionNumber(result.getInt("edition_number"));
                magazine.setMonthAppearance(result.getInt("month_appearance"));

                magazines.add(magazine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazines;
    }


    public void saveMagazine(Magazine magazine) {
        int generatedId = itemRepository.saveItem(magazine);

        String insertMagazineSql = "INSERT INTO magazines (item_id, edition_number, month_appearance) VALUES (?,?,?)";

        Connection connection = DatabaseManager.getInstance().connect();
        if (generatedId != -1) {
            try (PreparedStatement magazineStmt = connection.prepareStatement(insertMagazineSql)) {
                magazineStmt.setInt(1, generatedId);
                magazineStmt.setInt(2, magazine.getEditionNumber());
                magazineStmt.setInt(3, magazine.getMonthAppearance());

                magazineStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error saving item");
        }
    }

    public void updateMagazine(Magazine magazine, int id) {
        itemRepository.updateItem(magazine, id);

        String updateMagazineSql = "UPDATE magazines SET edition_number = ?, month_appearance = ? WHERE item_id = ?";

        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement magazineStmt = connection.prepareStatement(updateMagazineSql)) {
            magazineStmt.setInt(1, magazine.getEditionNumber());
            magazineStmt.setInt(2, magazine.getMonthAppearance());
            magazineStmt.setInt(3, id);

            magazineStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteMagazine(int id) {
        String deleteMagazineSql = "DELETE FROM magazines WHERE item_id = ?";

        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement magazineStmt = connection.prepareStatement(deleteMagazineSql)) {
            magazineStmt.setInt(1, id);

            magazineStmt.executeUpdate();
            itemRepository.deleteItem(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
