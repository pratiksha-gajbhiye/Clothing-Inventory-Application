package com.BillingApp.serviceImpl;


import com.BillingApp.DTO.UserDTO;
import com.BillingApp.model.User;
import com.BillingApp.repository.UserRepository;
import com.BillingApp.services.UserProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private  UserRepository userRepo;

    @Override
    public UserDTO getProfile(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToDto(user);
    }

    @Override
    public UserDTO updateProfile(Long userId, UserDTO dto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setPostalCode(dto.getPostalCode());
        user.setAboutMe(dto.getAboutMe());

        User saved = userRepo.save(user);
        return mapToDto(saved);
    }

    private UserDTO mapToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setCountry(user.getCountry());
        dto.setPostalCode(user.getPostalCode());
        dto.setAboutMe(user.getAboutMe());
        dto.setActive(true);
        return dto;
    }

}
