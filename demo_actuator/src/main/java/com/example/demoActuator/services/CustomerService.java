package com.example.demoActuator.services;

import services.omnichannel.ws.uib.tn.com.transactionalws.PayeeListReply;
import services.omnichannel.ws.uib.tn.com.transactionalws.RequestChannel;

public interface CustomerService {

    PayeeListReply getPayeeList(String customerNumber, RequestChannel channel);

}
