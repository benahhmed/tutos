package com.example.demoActuator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class UibWsMessageHeader {

    private static final Logger LOGGER = LoggerFactory.getLogger(UibWsMessageHeader.class);

    /**
     * Generate Request ID (Override of Function with CustomerNumber)
     *
     * @param serviceCode
     * @param requestTime
     * @param customerNumber
     * @return String
     */
    private String getRequestId(String serviceCode, String requestTime, String customerNumber) {
        if (customerNumber == null || customerNumber.equals("undefined")) {
            customerNumber = "00000000";
        }
        LOGGER.info("Generating UIB WS REQUEST ID  : " + serviceCode + customerNumber + requestTime);
        return serviceCode + customerNumber + requestTime;
    }

//    /**
//     * Generate MessageHeader for Public WS
//     *
//     * @param messageHeader
//     * @param serviceCode
//     * @return MessageHeader
//     */
//    public MessageHeader generateMessageHeader(MessageHeader messageHeader, String serviceCode, String customerNumber) {
//        String requestTime = UibWsUtils.getCurrentDateTimeStamp();
//        String requestId = this.getRequestId(serviceCode, requestTime, customerNumber);
//        messageHeader.setRequestId(requestId);
//        messageHeader.setRequestTime(requestTime);
//        messageHeader.setRequestChannel(RequestChannel.MOB);
//        return messageHeader;
//    }
//
//    /**
//     * Generate MessageHeader for Private WS
//     *
//     * @param messageHeader
//     * @param serviceCode
//     * @return services.omnichannel.ws.uib.tn.com.privatews.MessageHeader
//     */
//    public services.omnichannel.ws.uib.tn.com.privatews.MessageHeader generateMessageHeader(services.omnichannel.ws.uib.tn.com.privatews.MessageHeader messageHeader, String serviceCode, String customerNumber) {
//        String requestTime = UibWsUtils.getCurrentDateTimeStamp();
//        String requestId = this.getRequestId(serviceCode, requestTime, customerNumber);
//        messageHeader.setRequestId(requestId);
//        messageHeader.setRequestTime(requestTime);
//        messageHeader.setRequestChannel(services.omnichannel.ws.uib.tn.com.privatews.RequestChannel.MOB);
//        return messageHeader;
//    }


    /**
     * Generate MessageHeader for Configuration WS
     *
     * @param messageHeader
     * @param serviceCode
     * @return services.omnichannel.ws.uib.tn.com.configurationws.MessageHeader
     */
    public services.omnichannel.ws.uib.tn.com.configurationws.MessageHeader generateMessageHeader(services.omnichannel.ws.uib.tn.com.configurationws.MessageHeader messageHeader, String serviceCode, String customerNumber) {
        String requestTime = UibWsUtils.getCurrentDateTimeStamp();
        String requestId = this.getRequestId(serviceCode, requestTime, customerNumber);
        messageHeader.setRequestId(requestId);
        messageHeader.setRequestTime(requestTime);
        messageHeader.setRequestChannel(services.omnichannel.ws.uib.tn.com.configurationws.RequestChannel.MOB);
        return messageHeader;
    }


    /**
     * Generate MessageHeader for Transaction WS
     *
     * @param messageHeader
     * @param serviceCode
     * @return services.omnichannel.ws.uib.tn.com.transactionalws.MessageHeader
     */
    public services.omnichannel.ws.uib.tn.com.transactionalws.MessageHeader generateMessageHeader(services.omnichannel.ws.uib.tn.com.transactionalws.RequestChannel channel, services.omnichannel.ws.uib.tn.com.transactionalws.MessageHeader messageHeader, String serviceCode, String customerNumber) {
        String requestTime = UibWsUtils.getCurrentDateTimeStamp();
        String requestId = this.getRequestId(serviceCode, requestTime, customerNumber);
        messageHeader.setRequestId(requestId);
        messageHeader.setRequestTime(requestTime);
        messageHeader.setRequestChannel(channel!=null? channel : services.omnichannel.ws.uib.tn.com.transactionalws.RequestChannel.MOB );
        LOGGER.info("RequestChannel--- "+ messageHeader.getRequestChannel());
        return messageHeader;
    }

//    /**
//     * Generate MessageHeader for Monetics WS
//     *
//     * @param messageHeader
//     * @param serviceCode
//     * @return services.omnichannel.ws.uib.tn.com.moneticsws.MessageHeader
//     */
//    public services.omnichannel.ws.uib.tn.com.moneticsws.MessageHeader generateMessageHeader(services.omnichannel.ws.uib.tn.com.moneticsws.MessageHeader messageHeader, String serviceCode, String customerNumber) {
//        String requestTime = UibWsUtils.getCurrentDateTimeStamp();
//        String requestId = this.getRequestId(serviceCode, requestTime, customerNumber);
//        messageHeader.setRequestId(requestId);
//        messageHeader.setRequestTime(requestTime);
//        messageHeader.setRequestChannel(services.omnichannel.ws.uib.tn.com.moneticsws.RequestChannel.MOB);
//        return messageHeader;
//    }
//
//    public services.omnichannel.ws.uib.tn.com.Subscriptionws.MessageHeader generateMessageHeader(services.omnichannel.ws.uib.tn.com.Subscriptionws.MessageHeader messageHeader, String serviceCode, String customerNumber) {
//        String requestTime = UibWsUtils.getCurrentDateTimeStamp();
//        String requestId = this.getRequestId(serviceCode, requestTime, customerNumber);
//        messageHeader.setRequestId(requestId);
//        messageHeader.setRequestTime(requestTime);
//        messageHeader.setRequestChannel(services.omnichannel.ws.uib.tn.com.Subscriptionws.RequestChannel.MOB);
//        return messageHeader;
//    }

}
