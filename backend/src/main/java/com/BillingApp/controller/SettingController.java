package com.BillingApp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApp.model.CompanyProfile;
import com.BillingApp.model.PaymentType;
import com.BillingApp.model.Tax;
import com.BillingApp.services.SettingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService = null;

    @GetMapping("/profile")
    public ResponseEntity<CompanyProfile> getProfile() {
        return ResponseEntity.ok(settingService.getProfile());
    }

    @PostMapping("/profile")
    public ResponseEntity<CompanyProfile> saveProfile(@RequestBody CompanyProfile profile) {
        return ResponseEntity.ok(settingService.saveProfile(profile));
    }

    @GetMapping("/taxes")
    public ResponseEntity<List<Tax>> getTaxes() {
        return ResponseEntity.ok(settingService.getAllTaxes());
    }

    @PostMapping("/taxes")
    public ResponseEntity<Tax> saveTax(@RequestBody Tax tax) {
        return ResponseEntity.ok(settingService.saveTax(tax));
    }

    @DeleteMapping("/taxes/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        settingService.deleteTax(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/payment-types")
    public ResponseEntity<List<PaymentType>> getPaymentTypes() {
        return ResponseEntity.ok(settingService.getAllPaymentTypes());
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> payload) {
        settingService.changePassword(
            payload.get("email"),
            payload.get("oldPassword"),
            payload.get("newPassword")
        );
        return ResponseEntity.ok("Password updated successfully.");
    }
}
