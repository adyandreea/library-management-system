package com.andreea.library_management_system.entity;

public class Magazine extends Item {
    private int editionNumber;
    private int monthAppearance;

    public Magazine() {
    }

    public Magazine(int id, String title, int publishYear, boolean available, int editionNumber, int monthAppearance) {
        super(id, title, publishYear, available);
        this.editionNumber = editionNumber;
        this.monthAppearance = monthAppearance;
    }

    public int getEditionNumber() {
        return this.editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public int getMonthAppearance() {
        return this.monthAppearance;
    }

    public void setMonthAppearance(int monthAppearance) {
        this.monthAppearance = monthAppearance;
    }

    @Override
    public String toString() {
        return "Magazine{" + super.toString() +
                "editionNumber=" + editionNumber +
                ", monthAppearance=" + monthAppearance +
                '}';
    }
}
