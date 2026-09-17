package com.andreea.library_management_system.entity.interfaces;

import java.time.LocalDate;

public interface Borrowable {
    public void borrow();
    public void returned();
    public LocalDate calculateTheLimitedDate();
}
