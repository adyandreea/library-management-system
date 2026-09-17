package com.andreea.library_management_system.entity;

import com.andreea.library_management_system.entity.interfaces.Borrowable;

import java.time.LocalDate;

public class Book extends Item implements Borrowable {
    private String author;
    private String genre;
    private LocalDate returnDate;

    public Book(int id, String title, int publishYear, boolean available, String author, String genre){
        super(id, title, publishYear, available);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getGenre(){
        return this.genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    @Override
    public void borrow(){
        setAvailable(false);
        calculateTheLimitedDate();
    }

    @Override
    public void returned(){
        setAvailable(true);
        this.returnDate = null;
    }

    @Override
    public LocalDate calculateTheLimitedDate(){
        this.returnDate = LocalDate.now().plusDays(30);
        return returnDate;
    }

    public LocalDate getReturnDate(){
        return this.returnDate;
    }

    @Override
    public void showDetails() {
        System.out.println("Id: " + getId() +
                "\nTitle: " + getTitle() +
                "\nPublish Year: " + getPublishYear() +
                "\nAvailable: " + getAvailable() +
                "\nAuthor: " + author +
                "\nGenre: " + genre +
                "\nDue date: " + returnDate);
    }
}
