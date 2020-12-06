package com.proit.unitel.controller;

import com.proit.unitel.domain.AddClienteRequest;
import com.proit.unitel.domain.AddClienteResponse;
import com.proit.unitel.domain.EditarClienteRequest;
import com.proit.unitel.domain.EditarClienteResponse;
import com.proit.unitel.domain.ListClienteRequest;
import com.proit.unitel.domain.ListClienteResponse;
import com.proit.unitel.domain.RemoveClienteRequest;
import com.proit.unitel.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClienteEndpoint {
   
    @Autowired
    private ClienteService clienteService;
    private final String NAMESPACE = "http://proit.com/unitel/domain";
    
    /**
     * Operation to Save cliente
     * @param request
     * @return
     */
    @PayloadRoot(
        namespace  = NAMESPACE,
        localPart = "addClienteRequest"
    )
    @ResponsePayload
    public AddClienteResponse saveCliente(@RequestPayload  AddClienteRequest request) {
        return clienteService.add(request);
    }


    /**
     * Operation to list all clientes saved
     * @param listRequest
     * @return
     */
    @PayloadRoot(
        namespace = NAMESPACE,
        localPart = "listClienteRequest"
    )
    @ResponsePayload
    public ListClienteResponse listCliente(@RequestPayload ListClienteRequest listRequest) {
        return clienteService.list(listRequest);
    }

    /**
     * Operation to remove cliente
     * @param removeClienteRequest
     */
    @PayloadRoot(namespace = NAMESPACE, localPart = "removeClienteRequest")
    public void removeCliente(@RequestPayload RemoveClienteRequest removeClienteRequest) {
         clienteService.deleteById(removeClienteRequest.getId());
    }
    

    /**
     * Operation to update Cliente
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE, localPart = "editarClienteRequest")
    @ResponsePayload
    public EditarClienteResponse UpdateCliente(@RequestPayload EditarClienteRequest request) {
       return clienteService.update(request);
    }
}
