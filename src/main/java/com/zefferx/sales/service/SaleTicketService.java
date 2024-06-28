package com.zefferx.sales.service;

import com.zefferx.sales.exceptions.TicketNotFoundException;
import com.zefferx.sales.model.SaleTicket;
import com.zefferx.sales.repository.SaleTicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class SaleTicketService {
    private final SaleTicketRepository saleTicketRepository;

    SaleTicket createSaleTicket(Integer productId,
                                String productName,
                                Integer productPrice,
                                Integer clientId,
                                Integer clientQuantityRequired,
                                Integer totalSale){

        SaleTicket newSaleTicket = new SaleTicket(productId, productName, productPrice, clientId, clientQuantityRequired, totalSale);
        return saleTicketRepository.save(newSaleTicket);

    }

    public SaleTicket getSaleTicketById(Integer ticketId){
        return saleTicketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket no encontrado"));
    }

}
