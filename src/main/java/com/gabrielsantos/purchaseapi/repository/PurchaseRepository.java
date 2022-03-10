package com.gabrielsantos.purchaseapi.repository;

import com.gabrielsantos.purchaseapi.enums.PurchaseStatus;
import com.gabrielsantos.purchaseapi.model.Purchase;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE Purchase " +
                    "SET purchaseStatus = :purchaseStatus " +
                    "WHERE id = :purchaseId "
    )
    void setPurchaseStatus(@Param("purchaseId")Long purchaseId, @Param("purchaseStatus")PurchaseStatus purchaseStatus);

}
