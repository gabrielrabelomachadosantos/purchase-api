package com.gabrielsantos.purchaseapi.repository;

import com.gabrielsantos.purchaseapi.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {



}
