package com.andreea.library_management_system.service;

import com.andreea.library_management_system.entity.Magazine;
import com.andreea.library_management_system.repository.MagazineRepository;

import java.util.List;

public class MagazineService {

    private ItemService itemService;
    private MagazineRepository magazineRepository;

    public MagazineService() {
        this.itemService = new ItemService();
        this.magazineRepository = new MagazineRepository();
    }

    public Magazine getMagazineById(int id) {
        return magazineRepository.getMagazineById(id);
    }

    public List<Magazine> getAllMagazines() {
        return magazineRepository.getAllMagazines();
    }

    public void saveMagazine(Magazine magazine) {
        if (magazine.getMonthAppearance() <= 0 || magazine.getMonthAppearance() > 12) {
            System.out.println("Error: write an available month");
            return;
        }

        int generatedId = itemService.saveItem(magazine);

        if (generatedId != -1) {
            magazineRepository.saveMagazine(magazine, generatedId);
        } else {
            System.out.println("Error saving item");
        }
    }

    public void updateMagazine(Magazine magazine, int id) {
        if (magazine.getMonthAppearance() <= 0 || magazine.getMonthAppearance() > 12) {
            System.out.println("Error: write an available month");
            return;
        }

        itemService.updateItem(magazine, id);
        magazineRepository.updateMagazine(magazine, id);
    }

    public void deleteMagazine(int id) {
        magazineRepository.deleteMagazine(id);
        itemService.deleteItem(id);
    }
}
