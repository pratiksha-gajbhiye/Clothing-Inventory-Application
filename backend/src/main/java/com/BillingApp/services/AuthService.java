package com.BillingApp.services;

import com.BillingApp.DTO.AuthRequest;
import com.BillingApp.DTO.AuthResponse;
import com.BillingApp.model.SignupRequest;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    void signup(SignupRequest request);
}
