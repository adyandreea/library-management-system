package com.andreea.library_management_system.entity;

public abstract class Item {
    private int id;
    private String title;
    private int publishYear;
    private boolean available;

    public Item(int id, String title, int publishYear, boolean available){
        this.title = title;
        this.id = id;
        this.publishYear = publishYear;
        this.available = available;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getPublishYear(){
        return this.publishYear;
    }

    public void setPublishYear(int publishYear){
        this.publishYear = publishYear;
    }

    public boolean getAvailable(){
        return this.available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }

    public abstract void showDetails();
}
