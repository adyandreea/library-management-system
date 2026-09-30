package com.andreea.library_management_system.service;

import com.andreea.library_management_system.entity.Book;
import com.andreea.library_management_system.repository.BookRepository;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;
    private ItemService itemService;

    public BookService() {
        this.bookRepository = new BookRepository();
        this.itemService = new ItemService();
    }

    public Book getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void saveBook(Book book) {
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            System.out.println("Error: Must have an author");
            return;
        }

        if (book.getGenre() == null || book.getGenre().trim().isEmpty()) {
            System.out.println("Error: Must have a genre");
            return;
        }

        int generatedId = itemService.saveItem(book);

        if (generatedId != -1) {
            bookRepository.saveBook(book, generatedId);
        } else {
            System.out.println("Error saving item");
        }
    }

    public void updateBook(Book book, int id) {
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            System.out.println("Error: Must have an author");
            return;
        }

        if (book.getGenre() == null || book.getGenre().trim().isEmpty()) {
            System.out.println("Error: Must have a genre");
            return;
        }

        itemService.updateItem(book, id);
        bookRepository.updateBook(book, id);
    }

    public void deleteBook(int id) {
        bookRepository.deleteBook(id);
        itemService.deleteItem(id);
    }

    public void borrowBook(int id) {
        Book book = bookRepository.getBookById(id);

        if (book == null) {
            System.out.println("Book doesn't exist");
            return;
        }

        if (!book.getAvailable()) {
            System.out.println("Book isn't available");
            return;
        }

        book.borrow();
        itemService.updateItem(book, id);
        bookRepository.updateBook(book, id);
    }

    public void returnBook(int id) {
        Book book = bookRepository.getBookById(id);

        if (book == null) {
            System.out.println("Book doesn't exist");
            return;
        }

        if (book.getAvailable()) {
            System.out.println("Book is available");
            return;
        }

        book.returned();
        itemService.updateItem(book, id);
        bookRepository.updateBook(book, id);
    }
}
