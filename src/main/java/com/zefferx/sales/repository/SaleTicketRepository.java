package com.zefferx.sales.repository;

import com.zefferx.sales.model.Client;
import com.zefferx.sales.model.SaleTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTicketRepository extends JpaRepository<SaleTicket, Integer> {

}