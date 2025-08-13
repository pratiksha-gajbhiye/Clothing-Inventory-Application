package com.BillingApp.serviceImpl;


import com.BillingApp.DTO.UserDTO;
import com.BillingApp.model.Role;
import com.BillingApp.model.User;
import com.BillingApp.repository.RoleRepository;
import com.BillingApp.repository.UserRepository;
import com.BillingApp.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDTO dto) {
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered.");
        }

        Set<Role> roles = dto.getRoles().stream()
                .map(roleName -> roleRepo.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername() != null ? dto.getUsername() : dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(roles);

        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
