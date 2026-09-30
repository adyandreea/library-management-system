package com.andreea.library_management_system.service;

import com.andreea.library_management_system.entity.Item;
import com.andreea.library_management_system.repository.ItemRepository;

import java.time.LocalDate;

public class ItemService {

    private ItemRepository itemRepository;

    public ItemService() {
        this.itemRepository = new ItemRepository();
    }

    int currentYear = LocalDate.now().getYear();

    public int saveItem(Item item) {
        if (item.getTitle() == null || item.getTitle().trim().isEmpty()) {
            System.out.println("Error: Must have a title");
            return -1;
        }

        if (item.getPublishYear() <= 0 || item.getPublishYear() > currentYear) {
            System.out.println("Error: Write a valid year");
            return -1;
        }

        return itemRepository.saveItem(item);
    }

    public void updateItem(Item item, int id) {
        if (item.getTitle() == null || item.getTitle().trim().isEmpty()) {
            System.out.println("Error: Must have a title");
            return;
        }

        if (item.getPublishYear() <= 0 || item.getPublishYear() > currentYear) {
            System.out.println("Error: Write a valid year");
            return;
        }

        itemRepository.updateItem(item, id);
    }

    public void deleteItem(int id) {
        itemRepository.deleteItem(id);
    }
}
