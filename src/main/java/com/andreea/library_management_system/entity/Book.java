package com.andreea.library_management_system.entity;

import com.andreea.library_management_system.entity.interfaces.Borrowable;

import java.time.LocalDate;

public class Book extends Item implements Borrowable {
    private String author;
    private String genre;
    private LocalDate returnDate;

    public Book() {
    }

    public Book(int id, String title, int publishYear, boolean available, String author, String genre) {
        super(id, title, publishYear, available);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public void borrow() {
        setAvailable(false);
        calculateTheLimitedDate();
    }

    @Override
    public void returned() {
        setAvailable(true);
        this.returnDate = null;
    }

    @Override
    public LocalDate calculateTheLimitedDate() {
        this.returnDate = LocalDate.now().plusDays(30);
        return returnDate;
    }

    @Override
    public String toString() {
        return "Book{" + super.toString() +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", returnDate=" + returnDate +
                '}';
    }
}
