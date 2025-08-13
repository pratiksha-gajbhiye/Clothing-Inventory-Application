package com.BillingApp.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BillingApp.DTO.AuthRequest;
import com.BillingApp.DTO.AuthResponse;
import com.BillingApp.model.Role;
import com.BillingApp.model.SignupRequest;
import com.BillingApp.model.User;
import com.BillingApp.repository.RoleRepository;
import com.BillingApp.repository.UserRepository;
import com.BillingApp.services.AuthService;
import com.BillingApp.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired private UserRepository userRepo;
    @Autowired private RoleRepository roleRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    @Override
    public AuthResponse login(AuthRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        String roleName = user.getRole().stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("User has no role"))
            .getName();

        return new AuthResponse(token, roleName);
    }

    @Override
    public void signup(SignupRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Role role = roleRepo.findByName(request.getRole())
            .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Set.of(role)); // ✅ Fix here!

        userRepo.save(user);
    }
}
