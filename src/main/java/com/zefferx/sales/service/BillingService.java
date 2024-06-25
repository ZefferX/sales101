package com.zefferx.sales.service;

import com.zefferx.sales.dto.NewBillingRequest;
import com.zefferx.sales.dto.NewPurchaseRequest;
import com.zefferx.sales.dto.SaleResponse;
import com.zefferx.sales.dto.SaleTicket;
import com.zefferx.sales.model.Billing;
import com.zefferx.sales.model.Client;
import com.zefferx.sales.model.Product;
import com.zefferx.sales.repository.BillingRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class BillingService {
    private final BillingRepository billingRepository;
    private final ClientService clientService;
    private final ProductService productService;


    //crear dto para el request body
    //llamar al service
    //desde el service llamar al productService y clientService
    //buscarlos por id
    //empezar las condiciones que se hicieron en el proyecto java secuencial
    //guardar toodo
    //retorna un DTO de response con datos del ticket

    public SaleResponse completedSale(NewPurchaseRequest request) {

        Product product = productService.getProductById(request.productId());
        Client client = clientService.getClientById(request.clientId());
        if (client.getAge() < 18) {
            return new SaleResponse("Cliente no puede comprar por no tener edad requerida", null);
        }
        boolean esCantidadSolicitadaMayorALaDisponible = request.quantity() > product.getQuantity();
        if (esCantidadSolicitadaMayorALaDisponible && !request.isFlexibleClient())
            return new SaleResponse("No es posible comprar porque el cliente no es flexible en la cantidad", null) ;

        Integer totalCompra = 0;
        Integer cantidadAComprar = request.quantity();
        if (esCantidadSolicitadaMayorALaDisponible && request.isFlexibleClient()) {
            cantidadAComprar = product.getQuantity();

        }
        totalCompra = cantidadAComprar * product.getPrice();
        boolean tieneSuficienteDinero = client.getMoney() > totalCompra;
        if (!tieneSuficienteDinero)
            return new SaleResponse("Fondo insuficiente para continuar su compra", null) ;

        client.setMoney(client.getMoney() - totalCompra);
        Billing cashRegister = billingRepository.getReferenceById(Billing.staticId);
        cashRegister.setTotal(cashRegister.getTotal() + totalCompra);
        product.setQuantity(product.getQuantity() - cantidadAComprar);
        //No entiendo esta linea
        clientService.updateClientToInternalUse(client);
        productService.updateProductToInternalUse(product);
        billingRepository.save(cashRegister);

        SaleTicket saleTicket = new SaleTicket(product.getId(), product.getName(), product.getPrice(), client.getId(), cantidadAComprar, totalCompra);
        return new SaleResponse("Finalizado", saleTicket);

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