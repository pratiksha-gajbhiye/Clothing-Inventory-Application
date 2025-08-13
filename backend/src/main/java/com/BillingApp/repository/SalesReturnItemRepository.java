package com.BillingApp.repository;


import com.BillingApp.model.SalesReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesReturnItemRepository extends JpaRepository<SalesReturnItem, Long> {
    List<SalesReturnItem> findBySalesReturnId(Long salesReturnId);
}
