package com.BillingApp.services;


import com.BillingApp.DTO.UserDTO;

public interface UserProfileService {
    UserDTO getProfile(Long userId);
    UserDTO updateProfile(Long userId, UserDTO dto);
}
