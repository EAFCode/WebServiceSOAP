package com.proit.unitel.repository;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.proit.unitel.configuration.NotFoundException;
import com.proit.unitel.domain.Cliente;

import org.springframework.stereotype.Repository;



@Repository
public class ClienteRepository {
    
    //DataSource
    ArrayList<Cliente> tableCliente = new ArrayList();

    //save cliente
    public Cliente save(Cliente cliente) {
        cliente.setId(tableCliente.size()+1);
        tableCliente.add(cliente);
        return cliente;
    }

    //find all clientes
    public ArrayList<Cliente> findAll(int size){
        return(ArrayList)  tableCliente.stream().limit(size)
        .collect(Collectors.toList());
    }

    //remove cliente
    public void removeById(long id ){
        System.out.println("Entrou " + id);
        for(Cliente cliente: tableCliente){
            if (cliente.getId() == id) {
                System.out.println("apagado = " + id);
                tableCliente.remove(cliente);
                return;
            }  
        }

        throw new NotFoundException("Id "+ id + " Não foi encontrado");
            
    }


    //update cliente
    public Cliente update(Cliente clienteUpdate) {
        for (Cliente cliente : tableCliente) {
            if (cliente.getId() == clienteUpdate.getId()) {
                cliente.setNome(clienteUpdate.getNome());
                cliente.setEmail(clienteUpdate.getEmail());
                cliente.setTelefone(clienteUpdate.getTelefone());
                cliente.setEndereco(clienteUpdate.getEndereco());
                return cliente;
            }
        }

        throw new NotFoundException("Cliente id " + clienteUpdate.getId() + " Não foi encontrado");
    }

}
