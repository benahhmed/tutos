package com.example.demoActuator.Mapper;

import com.example.demoActuator.Model.ClientModel;
import com.example.demoActuator.entities.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public static ClientModel toModel(Client client) {
        ClientModel clientModel = new ClientModel();
        clientModel.setName(client.getName());
        clientModel.setCode("0000000");
        return clientModel;
    }

    public static List<ClientModel> toModels(List<Client> clientList) {
        List<ClientModel> clientModels = new ArrayList<>();
        if (clientList != null && !clientList.isEmpty()) {
            clientModels.addAll(clientList.stream().map(e -> toModel(e)).collect(Collectors.toList()));


        }
        return clientModels;
    }

}
