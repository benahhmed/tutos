package com.example.demoActuator.services;

import com.example.demoActuator.wsHelper.TransactionalCBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.omnichannel.ws.uib.tn.com.transactionalws.PayeeListReply;
import services.omnichannel.ws.uib.tn.com.transactionalws.RequestChannel;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private TransactionalCBHelper transactionalCBHelper;
    @Override
    public PayeeListReply getPayeeList(String customerNumber, RequestChannel channel) {
        return transactionalCBHelper.payeeList(customerNumber,channel);
    }
}
