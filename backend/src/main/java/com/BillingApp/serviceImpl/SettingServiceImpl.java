package com.BillingApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BillingApp.model.CompanyProfile;
import com.BillingApp.model.PaymentType;
import com.BillingApp.model.Tax;
import com.BillingApp.model.User;
import com.BillingApp.repository.CompanyProfileRepository;
import com.BillingApp.repository.PaymentTypeRepository;
import com.BillingApp.repository.TaxRepository;
import com.BillingApp.repository.UserRepository;
import com.BillingApp.services.SettingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {

	 @Autowired
	    private CompanyProfileRepository companyRepo;

	    @Autowired
	    private TaxRepository taxRepo;

	    @Autowired
	    private PaymentTypeRepository paymentRepo;

	    @Autowired
	    private UserRepository userRepo;

	    @Autowired
	    private PasswordEncoder encoder;

    @Override
    public CompanyProfile getProfile() {
        return companyRepo.findAll().stream().findFirst().orElse(new CompanyProfile());
    }

    @Override
    public CompanyProfile saveProfile(CompanyProfile profile) {
        return companyRepo.save(profile);
    }

    @Override
    public List<Tax> getAllTaxes() {
        return taxRepo.findAll();
    }

    @Override
    public Tax saveTax(Tax tax) {
        return taxRepo.save(tax);
    }

    @Override
    public void deleteTax(Long id) {
        taxRepo.deleteById(id);
    }

    @Override
    public List<PaymentType> getAllPaymentTypes() {
        return paymentRepo.findAll();
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password incorrect.");
        }

        user.setPassword(encoder.encode(newPassword));
        userRepo.save(user);
    }
}
