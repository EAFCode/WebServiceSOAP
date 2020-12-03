package com.proit.unitel.repository;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.proit.unitel.configuration.NotFoundException;
import com.proit.unitel.domain.Cliente;

import org.springframework.stereotype.Repository;



@Repository
public class ClienteRepository {
    
    ArrayList<Cliente> tableCliente = new ArrayList();

    public Cliente save(Cliente cliente) {
        cliente.setId(tableCliente.size()+1);
        tableCliente.add(cliente);
        return cliente;
    }

    public ArrayList<Cliente> findAll(int size){
        return(ArrayList)  tableCliente.stream().limit(size)
        .collect(Collectors.toList());
    }

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

    

}
