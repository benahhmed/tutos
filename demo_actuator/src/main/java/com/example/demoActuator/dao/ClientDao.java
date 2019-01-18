package com.example.demoActuator.dao;

import com.example.demoActuator.entities.Client;

import java.util.List;

public interface ClientDao {

    Client save(Client client);

    List<Client> listClients();

    boolean bindAuthLDAP(String customerNumber, String password) throws Exception;

    void saveCustomerLDAP(String customerNumber, String password, String lastName, String firstName);
}
