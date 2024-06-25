package com.zefferx.sales.service;

import com.zefferx.sales.dto.NewClientRequest;
import com.zefferx.sales.model.Client;
import com.zefferx.sales.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getClients(){
        return  clientRepository.findAll();
    }

    //Todo lo que sigue aca se debe hacer manualmente? O habia forma de hacerlo automaticamente?

    public void addClient (NewClientRequest request){
        Client newClient = new Client();
        newClient.setMoney(request.money());
        newClient.setAge(request.age());
        clientRepository.save(newClient);
    }

    public String deleteClient(Integer id){
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) return "Cliente con ID " + id + " no encontrado";
        clientRepository.deleteById(id);
        return "Cliente eliminado con exito";
    }

    public Client getClientById(Integer id){
        Client clienteEspecifico = clientRepository.findById(id).get();
        return clienteEspecifico;
    }

    public Client updateClient(Integer clientId, NewClientRequest request) {
        Client updateClient = clientRepository.findById(clientId).get();
        updateClient.setMoney(request.money());
        return clientRepository.save(updateClient);
    }

    public Client updateClientToInternalUse(Client client) {
        return clientRepository.save(client);
    }
}
