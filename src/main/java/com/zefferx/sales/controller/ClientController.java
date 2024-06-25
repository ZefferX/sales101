package com.zefferx.sales.controller;

import com.zefferx.sales.dto.NewClientRequest;
import com.zefferx.sales.exceptions.ClientNotFoundException;
import com.zefferx.sales.model.Client;
import com.zefferx.sales.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@AllArgsConstructor

public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<Client> getClients(){
        return  clientService.getClients();
    }

    @PostMapping
    public void addClient (@RequestBody NewClientRequest request){
        clientService.addClient(request);
    }

    @DeleteMapping("/{Id}")
    public String deleteClient(@PathVariable("Id")Integer id) throws ClientNotFoundException {
        return clientService.deleteClient(id);
    }

    @GetMapping("/{Id}")
    public Client getClientById(@PathVariable("Id")Integer id){
        return clientService.getClientById(id);
    }

    @PutMapping("/{Id}")
    public Client updateClient(@PathVariable Integer Id, @RequestBody NewClientRequest request){
        return clientService.updateClient(Id, request);
    }


}
