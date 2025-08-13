package com.BillingApp.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.SalesReturn;

public interface SalesReturnRepository extends JpaRepository<SalesReturn, Long> {
    List<SalesReturn> findByReturnDateBetween(LocalDateTime from, LocalDateTime to);

//	List<SalesReturn> findByDateBetween(LocalDate from, LocalDate to);

	List<SalesReturn> findByReturnDateBetween(LocalDate from, LocalDate to);

}
