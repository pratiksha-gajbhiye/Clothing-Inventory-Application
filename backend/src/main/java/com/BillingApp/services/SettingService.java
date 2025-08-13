package com.BillingApp.services;

import java.util.List;

import com.BillingApp.model.CompanyProfile;
import com.BillingApp.model.PaymentType;
import com.BillingApp.model.Tax;

public interface SettingService {
    CompanyProfile getProfile();
    CompanyProfile saveProfile(CompanyProfile profile);

    List<Tax> getAllTaxes();
    Tax saveTax(Tax tax);
    void deleteTax(Long id);

    List<PaymentType> getAllPaymentTypes();

    void changePassword(String email, String oldPassword, String newPassword);
}
