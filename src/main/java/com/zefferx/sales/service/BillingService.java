package com.zefferx.sales.service;

import com.zefferx.sales.dto.*;
import com.zefferx.sales.model.Billing;
import com.zefferx.sales.model.Client;
import com.zefferx.sales.model.Product;
import com.zefferx.sales.model.SaleTicket;
import com.zefferx.sales.repository.BillingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class BillingService {
    private final BillingRepository billingRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final SaleTicketService saleTicketService;


    public SaleResponse completedSale(NewPurchaseRequest request) {

        Product product = productService.getProductById(request.productId());
        Client client = clientService.getClientById(request.clientId());
        if (client.getAge() < 18) {
            return new SaleResponse("Cliente no puede comprar por no tener edad requerida", null);
        }
        boolean esCantidadSolicitadaMayorALaDisponible = request.quantity() > product.getQuantity();
        if (esCantidadSolicitadaMayorALaDisponible && !request.isFlexibleClient())
            return new SaleResponse("No es posible comprar porque el cliente no es flexible en la cantidad", null);

        Integer totalCompra = 0;
        Integer cantidadAComprar = request.quantity();
        if (esCantidadSolicitadaMayorALaDisponible && request.isFlexibleClient()) {
            cantidadAComprar = product.getQuantity();

        }
        totalCompra = cantidadAComprar * product.getPrice();
        boolean tieneSuficienteDinero = client.getMoney() > totalCompra;
        if (!tieneSuficienteDinero)
            return new SaleResponse("Fondo insuficiente para continuar su compra", null);


        client.setMoney(client.getMoney() - totalCompra);
        Billing cashRegister = billingRepository.findFirstByOrderByIdDesc();
        Integer currentMoney = cashRegister.getTotal();
        Billing newCashRegister = new Billing();

        newCashRegister.setTotal(currentMoney + totalCompra);
        product.setQuantity(product.getQuantity() - cantidadAComprar);
        clientService.updateClientToInternalUse(client);
        productService.updateProductToInternalUse(product);
        billingRepository.save(newCashRegister);


        SaleTicket newSaleTicketResponse = saleTicketService.createSaleTicket(product.getId(),
                product.getName(),
                product.getPrice(),
                client.getId(),
                cantidadAComprar,
                totalCompra);

        SaleTicketResponse saleTicketResponse = new SaleTicketResponse
                (product.getId(),
                        product.getName(),
                        product.getPrice(),
                        client.getId(),
                        cantidadAComprar,
                        totalCompra);
        return new SaleResponse("Finalizado", saleTicketResponse);

    }

    public DevolutionResponse originalTicket(ReturnPurchaseRequest request) {

        SaleTicket originalTicket = saleTicketService.getSaleTicketById(request.ticketId());

        Product product = productService.getProductById(originalTicket.getProductId());
        Client client = clientService.getClientById(originalTicket.getClientId());

        if (request.quantity() > originalTicket.getClientQuantityRequired()) {
            return new DevolutionResponse("Cantidad a devolver superior a la comprada, operacion imposible", null);
        }

        Integer totalToReturn = request.quantity() * product.getPrice();
        Billing cashRegister = billingRepository.findFirstByOrderByIdDesc();
        Integer currentMoney = cashRegister.getTotal();

        boolean cashRegisterMoneyIsEnough = totalToReturn <=  currentMoney;
        if (!cashRegisterMoneyIsEnough)
            return new DevolutionResponse("No tenemos dinero suficiente para realizar su devolucion, vuelva mas tarde", null);

        product.setQuantity(product.getQuantity() + request.quantity());
        client.setMoney(client.getMoney() + totalToReturn);

        Billing newCashRegister = new Billing();
        newCashRegister.setTotal(currentMoney - totalToReturn);

        productService.updateProductToInternalUse(product);
        clientService.updateClientToInternalUse(client);
        billingRepository.save(newCashRegister);

        SaleTicket devolutionTicket = saleTicketService.createSaleTicket(
                product.getId(),
                product.getName(),
                product.getPrice(),
                client.getId(),
                request.quantity(),
                -totalToReturn
                );

        DevolutionTicketResponse devolutionTicketResponse = new DevolutionTicketResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                client.getId(),
                request.quantity(),
                -totalToReturn
        );
        return new DevolutionResponse("Devolucion completada", devolutionTicketResponse);


    }

}













/*
    public Billing getCashRegister(){
        return billingRepository.findAll().getFirst();
    }

    public Billing updateCashRegister(NewBillingRequest request){
        Billing sumatotal = billingRepository.findById(Billing.staticId).get();
        sumatotal.setTotal(request.totalcaja());
        return billingRepository.save(sumatotal);
    }
    //Esto creo que no es lo correcto ya que daria posibilidad a agregar mas cajas y lo ideal seria
    // que simplemente haya una, pero es para poder ocntinuar con el ejercicio ahora

    public void addCashRegister (NewBillingRequest request){
        Billing caja = new Billing();
        caja.setTotal(request.totalcaja());
        billingRepository.save(caja);
    }
}
*/