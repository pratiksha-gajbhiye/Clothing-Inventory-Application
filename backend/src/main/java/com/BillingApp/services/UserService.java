package com.BillingApp.services;


import java.util.List;

import com.BillingApp.DTO.UserDTO;
import com.BillingApp.model.User;

public interface UserService {
    com.BillingApp.model.User createUser(UserDTO dto);
    List<User> getAllUsers();
}
