package com.zefferx.sales.repository;

import com.zefferx.sales.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {
    Billing findFirstByOrderByIdDesc();

}
