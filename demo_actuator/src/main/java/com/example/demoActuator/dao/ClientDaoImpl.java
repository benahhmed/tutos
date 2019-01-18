package com.example.demoActuator.dao;

import com.example.demoActuator.NewLdapServiceUtil;
import com.example.demoActuator.entities.Client;
import com.example.demoActuator.entities.LdapUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {


    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDaoImpl.class);

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private NewLdapServiceUtil ldapService;




    @Override
    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @Override
    public boolean bindAuthLDAP(String customerNumber, String password) throws Exception {

        try {
            LOGGER.info("[ClientDaoImpl] bindAuthLDAP");
            return ldapService.authenticateUser(customerNumber, password);
            //return true;
        } catch (Exception e) {
            LOGGER.error("[ClientDaoImpl] failed bindAuthLDAP");
            throw e;

//            if (e instanceof ApplicationException) {
//                throw e;
//            }
//            else {
//                LOGGER.error("Unrecognized exception when trying to bind authentication customer : " + customerNumber);
//                throw new ApplicationException(ReturnCodes.E200.getCode(), ReturnCodes.E200.getMessage());
//            }
        }
    }

    @Override
    public void saveCustomerLDAP(String customerNumber, String password, String lastName, String firstName) {
        LdapUser ldapUser = LdapUser.newBuilder().userName(customerNumber).password(password).lastName(lastName).firstName(firstName).build();
        ldapService.create(ldapUser);
    }
}
