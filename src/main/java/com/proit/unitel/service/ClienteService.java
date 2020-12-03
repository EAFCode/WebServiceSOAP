package com.proit.unitel.service;

import java.util.List;
import java.util.stream.Collectors;

import com.proit.unitel.domain.AddClienteRequest;
import com.proit.unitel.domain.AddClienteResponse;
import com.proit.unitel.domain.Cliente;
import com.proit.unitel.domain.ListClienteRequest;
import com.proit.unitel.domain.ListClienteResponse;
import com.proit.unitel.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    public AddClienteResponse add( AddClienteRequest clienteRequest ) {

        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setEndereco(clienteRequest.getEndereco());
        cliente.setTelefone(clienteRequest.getTelefone());

        AddClienteResponse response = new AddClienteResponse();
        response.setCliente(repository.save(cliente));
        return response;
    }

    public ListClienteResponse list(ListClienteRequest request) {

        int size = request.getSize();

       List<Cliente> clientes = repository.findAll(size);

        ListClienteResponse response = new ListClienteResponse();
        response.getClientes().addAll(clientes);
        return response;
    }

    public void deleteById(long id){
        repository.removeById(id);
    }
}
