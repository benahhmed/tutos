package com.example.demoActuator.controllers;

import com.example.demoActuator.Mapper.ClientMapper;
import com.example.demoActuator.Model.ClientModel;
import com.example.demoActuator.common.*;
import com.example.demoActuator.dao.ClientDao;
import com.example.demoActuator.entities.Client;
import com.example.demoActuator.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.omnichannel.ws.uib.tn.com.transactionalws.PayeeListReply;
import services.omnichannel.ws.uib.tn.com.transactionalws.RequestChannel;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    private ClientDao clientDao;

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "welcome")
    public String getMessage() {
        return "Welcome to the application";
    }

    @PostMapping(value = "/addClient")
    public Client saveClient(@RequestBody Client client) {
        return clientDao.save(client);
    }

    @GetMapping(value = "/getClientList")
    public List<Client> clientList() {
        return clientDao.listClients();
    }

    @GetMapping(value = "/getClientListModels")
    public List<ClientModel> getClientListModels() {
        List<ClientModel> clientModels = ClientMapper.toModels(clientDao.listClients());
        return clientModels;
    }

    @GetMapping(value = "/ClientList")
    public GenericResponse ClientList() {
        return ResponseBuilder.buildSuccessResponse(new ListResponse(clientDao.listClients()), Provider.COMPANY);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public GenericResponse login(@RequestParam String customerNumber, @RequestParam String password) throws Exception {
        return ResponseBuilder.buildSuccessResponse(new BooleanResponse(clientDao.bindAuthLDAP(customerNumber, password)), Provider.COMPANY);
    }


    @RequestMapping(value = "/saveCustomerLDAP", method = RequestMethod.GET)
    public GenericResponse saveCustomerLDAP(@RequestParam String customerNumber, @RequestParam String password
            , @RequestParam String lastName, @RequestParam String firstName) {
        clientDao.saveCustomerLDAP(customerNumber, password, lastName, firstName);
        return ResponseBuilder.buildSuccessResponse(new BooleanResponse(true), Provider.COMPANY);
    }

    @RequestMapping(value = "/transactional/getPayeeList", method= RequestMethod.GET)
    public GenericResponse getPayeeList(@RequestParam String customerId,@RequestParam(required=false, name = "requestedChannel") RequestChannel channel)
            throws ApplicationException, Exception{
        PayeeListReply payeeList = customerService.getPayeeList(customerId,channel);
        return ResponseBuilder.buildSuccessResponse(new ObjectResponse(payeeList), Provider.COMPANY);
    }

}
