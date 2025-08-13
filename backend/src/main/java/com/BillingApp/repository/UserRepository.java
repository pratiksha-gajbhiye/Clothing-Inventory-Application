package com.BillingApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);  // ✅ Add this line

}

