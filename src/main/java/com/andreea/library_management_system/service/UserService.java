package com.andreea.library_management_system.service;

import com.andreea.library_management_system.entity.User;
import com.andreea.library_management_system.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            System.out.println("Error: username doesn't exist");
            return false;
        }

        if (user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
