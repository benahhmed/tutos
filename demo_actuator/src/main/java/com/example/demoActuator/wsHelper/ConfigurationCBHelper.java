package com.example.demoActuator.wsHelper;

import com.example.demoActuator.common.ApplicationException;
import com.example.demoActuator.utils.UibWsMessageHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import services.omnichannel.ws.uib.tn.com.configurationws.*;

import javax.xml.ws.BindingProvider;

@Component
public class ConfigurationCBHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationCBHelper.class.getName());

	private static Configuration configurationWs;

	private static UibWsMessageHeader uibWsMessageHeader;

	@Value("${uib.ws.configuration.endpoint}")
    private String endpoint;

	public ConfigurationCBHelper() {
        if(ConfigurationCBHelper.configurationWs == null){
            Configuration_Service configuration_Service = new Configuration_Service();
            ConfigurationCBHelper.configurationWs = configuration_Service.getConfigurationPort();
        }
        if(ConfigurationCBHelper.uibWsMessageHeader == null){
            ConfigurationCBHelper.uibWsMessageHeader = new UibWsMessageHeader();
        }
	}
	
	/**
	 * Set WS Endpoint
	 * @param operation
	 */
	public void setEndpoint(String operation){
		String url = endpoint ;//+ "/" + operation;
		
		BindingProvider bindingProvider = (BindingProvider) configurationWs;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
	}
	
	/**
	 * SetParameter WS
	 * @param parameterKey
	 * @param description
	 * @param type
	 * @param value
	 * @return JSONObject
	 */
	public SetParameterReply setParameter(String parameterKey, String description, String type, String value, String customerNumber){
		try{
			setEndpoint("SetParameter");
			
			SetParameterRequest setParameterRequest = new SetParameterRequest();
			
			// Setting Header
			setParameterRequest.setMessageHeader(uibWsMessageHeader.generateMessageHeader(new MessageHeader(), "SP", customerNumber));
			
			// Setting Parameters
			SetParameterRequestBody setParameterRequestBody = new SetParameterRequestBody();
			setParameterRequestBody.setParameterKey(parameterKey);
			setParameterRequestBody.setDescription(description);
			try{
				setParameterRequestBody.setType(Types.fromValue(type));
			}catch(Exception e){
				e.printStackTrace();
				// Using Types.STRING as default
				setParameterRequestBody.setType(Types.STRING);
			}
			setParameterRequestBody.setValue(value);
			// Setting Body
			setParameterRequest.setMessageBody(setParameterRequestBody);
			
			// Invoke the WS
			SetParameterReply setParameterReply = configurationWs.setParameter(setParameterRequest);
			
			// Return Response as JSON
			return setParameterReply;
		} catch (Exception e) {
			LOGGER.error("An error has occurred when trying to set Parameter " + e.getMessage());
			throw new ApplicationException("100", "An error has occurred when trying to set Parameter  ");
		}
	}

}