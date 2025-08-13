package com.BillingApp.config;

import com.BillingApp.model.Role;
import com.BillingApp.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class RoleDataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public void run(String... args) {
        if (!roleRepo.findByName("USER").isPresent()) {
            roleRepo.save(new Role(null, "USER"));
        }
        if (!roleRepo.findByName("ADMIN").isPresent()) {
            roleRepo.save(new Role(null, "ADMIN"));
        }
    }
}
