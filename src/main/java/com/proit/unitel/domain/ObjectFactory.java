//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.01 at 11:51:16 PM WAT 
//


package com.proit.unitel.domain;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.proit.unitel.domain package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.proit.unitel.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddClienteRequest }
     * 
     */
    public AddClienteRequest createAddClienteRequest() {
        return new AddClienteRequest();
    }

    /**
     * Create an instance of {@link ListClienteRequest }
     * 
     */
    public ListClienteRequest createListClienteRequest() {
        return new ListClienteRequest();
    }

    /**
     * Create an instance of {@link ListClienteResponse }
     * 
     */
    public ListClienteResponse createListClienteResponse() {
        return new ListClienteResponse();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link AddClienteResponse }
     * 
     */
    public AddClienteResponse createAddClienteResponse() {
        return new AddClienteResponse();
    }

    /**
     * Create an instance of {@link RemoveClienteRequest }
     * 
     */
    public RemoveClienteRequest createRemoveClienteRequest() {
        return new RemoveClienteRequest();
    }

}