package com.andreea.library_management_system.repository;

import com.andreea.library_management_system.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookRepository {

    private ItemRepository itemRepository;

    public BookRepository() {
        this.itemRepository = new ItemRepository();
    }

    public Book getBookById(int id) {
        String selectBookByIdSql = "SELECT items.id, items.title, items.publish_year, items.available, books.author, books.genre, books.return_date " +
                "FROM items " +
                "JOIN books ON items.id = books.item_id " +
                "WHERE items.id = ?";

        Book book = null;
        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement bookStmt = connection.prepareStatement(selectBookByIdSql)) {
            bookStmt.setInt(1, id);

            try (ResultSet result = bookStmt.executeQuery()) {
                if (result.next()) {
                    book = new Book();
                    book.setId(result.getInt("id"));
                    book.setTitle(result.getString("title"));
                    book.setPublishYear(result.getInt("publish_year"));
                    book.setAvailable(result.getBoolean("available"));
                    book.setAuthor(result.getString("author"));
                    book.setGenre(result.getString("genre"));

                    LocalDate returnDate = result.getObject("return_date", LocalDate.class);
                    book.setReturnDate(returnDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public ArrayList<Book> getAllBooks() {
        String getAllBooksSql = "SELECT items.id, items.title, items.publish_year, items.available, books.author, books.genre, books.return_date " +
                "FROM items " +
                "JOIN books ON items.id = books.item_id ";

        Connection connection = DatabaseManager.getInstance().connect();
        ArrayList<Book> books = new ArrayList<>();
        try (PreparedStatement bookStmt = connection.prepareStatement(getAllBooksSql)) {
            ResultSet result = bookStmt.executeQuery();
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setPublishYear(result.getInt("publish_year"));
                book.setAvailable(result.getBoolean("available"));
                book.setAuthor(result.getString("author"));
                book.setGenre(result.getString("genre"));

                LocalDate returnDate = result.getObject("return_date", LocalDate.class);
                book.setReturnDate(returnDate);

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void saveBook(Book book) {
        int generatedId = itemRepository.saveItem(book);

        if (generatedId != -1) {
            String insertBookSql = "INSERT INTO books (item_id, author, genre) VALUES (?,?,?)";

            Connection connection = DatabaseManager.getInstance().connect();
            try (PreparedStatement bookStmt = connection.prepareStatement(insertBookSql)) {
                bookStmt.setInt(1, generatedId);
                bookStmt.setString(2, book.getAuthor());
                bookStmt.setString(3, book.getGenre());

                bookStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error saving item");
        }
    }

    public void updateBook(Book book, int id) {
        itemRepository.updateItem(book, id);

        String updateBookSql = "UPDATE books SET author = ?, genre = ? WHERE item_id = ?";

        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement bookStmt = connection.prepareStatement(updateBookSql)) {
            bookStmt.setString(1, book.getAuthor());
            bookStmt.setString(2, book.getGenre());
            bookStmt.setInt(3, id);

            bookStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteBook(int id) {
        String deleteBookSql = "DELETE FROM books WHERE item_id = ?";

        Connection connection = DatabaseManager.getInstance().connect();
        try (PreparedStatement bookStmt = connection.prepareStatement(deleteBookSql)) {
            bookStmt.setInt(1, id);

            bookStmt.executeUpdate();
            itemRepository.deleteItem(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
