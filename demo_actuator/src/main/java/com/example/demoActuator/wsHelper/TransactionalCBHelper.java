package com.example.demoActuator.wsHelper;

import com.example.demoActuator.common.ApplicationException;
import com.example.demoActuator.utils.UibWsMessageHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import services.omnichannel.ws.uib.tn.com.transactionalws.*;

import javax.xml.ws.BindingProvider;

@Component
public class TransactionalCBHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionalCBHelper.class.getName());

	private static Transactional transactionalWs;

	private static UibWsMessageHeader uibWsMessageHeader;

	@Value("${uib.ws.transactional.endpoint}")
	private String endpoint;

	public TransactionalCBHelper() {
		if(TransactionalCBHelper.transactionalWs == null){
			Transactional_Service transactional_Service = new Transactional_Service();
			TransactionalCBHelper.transactionalWs = transactional_Service.getTransactionalPort();
		}
		if(TransactionalCBHelper.uibWsMessageHeader == null){
			TransactionalCBHelper.uibWsMessageHeader = new UibWsMessageHeader();
		}
	}

	/**
	 * Set WS Endpoint
	 * 
	 * @param operation
	 */
	public void setEndpoint(String operation) {
		String url =  endpoint ;//+ "/" + operation;

		BindingProvider bindingProvider = (BindingProvider) transactionalWs;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
	}

	/**
	 * AddPayee WS
	 * 
	 * @param payeeLastName
	 * @param payeeFirstName
	 * @param payeeBank
	 * @param payeeRIB
	 * @return JSONObject
	 */
	public AddPayeeReply addPayee(RequestChannel channel,String payeeLastName, String payeeFirstName, String payeeBank, String payeeRIB, String clientCode) {
		try {
			setEndpoint("AddPayee");
			AddPayeeRequest addPayeeRequest = new AddPayeeRequest();
			addPayeeRequest.setMessageHeader(uibWsMessageHeader.generateMessageHeader(channel,new MessageHeader(), "AP", clientCode));

			AddPayeeRequestBody addPayeeRequestBody = new AddPayeeRequestBody();
			addPayeeRequestBody.setCustomerId(clientCode);
			// Creating the Payee Object
			Payee payee = new Payee();
			payee.setPayeeLastName(payeeLastName);
			payee.setPayeeFirstName(payeeFirstName);
			payee.setPayeeBank(payeeBank);
			payee.setPayeeRIB(payeeRIB);

			addPayeeRequestBody.setPayee(payee);

			addPayeeRequest.setMessageBody(addPayeeRequestBody);

			AddPayeeReply addPayeeReply = transactionalWs.addPayee(addPayeeRequest);

			return addPayeeReply;
		}catch(Exception e){
			LOGGER.error("An error has occurred when trying to add payee " + e.getMessage());
			throw new ApplicationException("100", e.getMessage());
		}
	}

	/**
	 * EligibleAccountList WS
	 * 
	 * @return EligibleAccountListReply
	 */
	public EligibleAccountListReply eligibleAccountList(String clientCode,RequestChannel channel) {
		try {
			setEndpoint("EligibleAccountList");
			EligibleAccountListRequest eligibleAccountListRequest = new EligibleAccountListRequest();
			eligibleAccountListRequest
					.setMessageHeader(uibWsMessageHeader.generateMessageHeader(channel,new MessageHeader(), "EA", clientCode));
			EligibleAccountListRequestBody eligibleAccountListRequestBody = new EligibleAccountListRequestBody();
			eligibleAccountListRequestBody.setCustomerId(clientCode);
			eligibleAccountListRequest.setMessageBody(eligibleAccountListRequestBody);
			EligibleAccountListReply eligibleAccountListReply = transactionalWs
					.eligibleAccountList(eligibleAccountListRequest);

			return eligibleAccountListReply;
		}catch(Exception e){
			LOGGER.error("An error has occurred when trying to get eligible accounts list " + e.getMessage());
			throw new ApplicationException("100", e.getMessage());
		}
	}

	/**
	 * DeletePayee WS
	 * 
	 * @param payeeRIB
	 * @return JSONObject
	 */
	public DeletePayeeReply deletePayee(RequestChannel channel,String payeeRIB, String clientCode) {
		try {
			setEndpoint("DeletePayee");
			DeletePayeeRequest deletePayeeRequest = new DeletePayeeRequest();
			deletePayeeRequest.setMessageHeader(uibWsMessageHeader.generateMessageHeader(channel,new MessageHeader(), "DP", clientCode));

			DeletePayeeRequestBody deletePayeeRequestBody = new DeletePayeeRequestBody();
			deletePayeeRequestBody.setCustomerId(clientCode);
			deletePayeeRequestBody.setPayeeRIB(payeeRIB);

			deletePayeeRequest.setMessageBody(deletePayeeRequestBody);

			DeletePayeeReply deletePayeeReply = transactionalWs.deletePayee(deletePayeeRequest);

		return deletePayeeReply;
		}catch(Exception e){
			LOGGER.error("An error has occurred when trying to delete payee " + e.getMessage());
			throw new ApplicationException("100", e.getMessage());
		}
	}

	/**
	 * OrderChequeBook WS
	 * 
	 * @param accountRIB
	 * @return JSONObject
	 */
	public OrderChequeBookReply orderChequeBook(RequestChannel channel,String accountRIB, String clientCode) {
		try {
			setEndpoint("OrderChequeBook");
			OrderChequeBookRequest orderChequeBookRequest = new OrderChequeBookRequest();
			orderChequeBookRequest
					.setMessageHeader(uibWsMessageHeader.generateMessageHeader(channel,new MessageHeader(), "CB", clientCode));

			OrderChequeBookRequestBody orderChequeBookRequestBody = new OrderChequeBookRequestBody();
			orderChequeBookRequestBody.setCustomerId(clientCode);
			orderChequeBookRequestBody.setAccountRIB(accountRIB);

			orderChequeBookRequest.setMessageBody(orderChequeBookRequestBody);

			OrderChequeBookReply orderChequeBookReply = transactionalWs
					.orderChequeBook(orderChequeBookRequest);

				return orderChequeBookReply;
		}catch(Exception e){
			LOGGER.error("An error has occurred when trying to order cheque book " + e.getMessage());
			throw new ApplicationException("100", e.getMessage());
		}
	}

	/**
	 * PayeeList WS
	 * 
	 * @return JSONObject
	 */
	public PayeeListReply payeeList(String clientCode, RequestChannel channel) {
		try {
			setEndpoint("PayeeList");
			PayeeListRequest payeeListRequest = new PayeeListRequest();
			payeeListRequest.setMessageHeader(uibWsMessageHeader.generateMessageHeader(channel,new MessageHeader(), "PL", clientCode));

			PayeeListRequestBody payeeListRequestBody = new PayeeListRequestBody();
			payeeListRequestBody.setCustomerId(clientCode);

			payeeListRequest.setMessageBody(payeeListRequestBody);

			PayeeListReply payeeListReply = transactionalWs.payeeList(payeeListRequest);

			return payeeListReply;
		}catch(Exception e){
			LOGGER.error("An error has occurred when trying to get payee list " + e.getMessage());
			throw new ApplicationException("100", e.getMessage());
		}
	}

	
	/**
	 * This method aims to allow a given customer to cancel a permanent transfer
	 * from the list of permanent transfers associated with the customer
	 * @author radhouene.gniwa
	 * @param customerId : The customer number
	 * @param permanentTransferReference: The reference of the permanent transfer to cancel
	 * @return {@link CancelPermanentTransferReply}
	 * @throws ApplicationException
	 */
	public CancelPermanentTransferReply cancelPermanentTransfer(RequestChannel channel,String customerId, String permanentTransferReference) throws ApplicationException{
		try {
			setEndpoint("CancelPermanentTransfer"); 
			CancelPermanentTransferRequest cancelTransferRequest = new CancelPermanentTransferRequest();
			cancelTransferRequest.setMessageHeader(uibWsMessageHeader.generateMessageHeader(channel,new MessageHeader(), "CP", customerId));

			CancelPermanentTransferRequestBody cancelTransferRequestBody = new CancelPermanentTransferRequestBody();

			// Setting Parameters
			cancelTransferRequestBody.setCustomerId(customerId);
			cancelTransferRequestBody.setPermanentTransferReference(permanentTransferReference);
			cancelTransferRequest.setMessageBody(cancelTransferRequestBody);
			CancelPermanentTransferReply cancelTransferReply = transactionalWs.cancelPermanentTransfer(cancelTransferRequest);
			return cancelTransferReply;
		}catch(Exception e){
			LOGGER.error("An error has occurred when trying to cancel permanent transfer " + e.getMessage());
			throw new ApplicationException("100", e.getMessage());
		}
	}
	

	
}
