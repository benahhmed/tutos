package com.example.demoActuator.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

//import com.proxymit.assets.utils.SecurityUtils;

public class UibWsUtils {
	
	public static String getClientCode(){
		String requestIssuerUserId = "00000000";    
		try {
			// If Authenticated
//			requestIssuerUserId = SecurityUtils.getUser("id");
			if(requestIssuerUserId == null || requestIssuerUserId.equals("Null")){
				requestIssuerUserId = "00000000";
			}
		} catch (Exception e) {
			// If not authenticated 
			requestIssuerUserId = "00000000";
		}
		return requestIssuerUserId;
	}
	
	 public static String getCurrentDateTimeStamp() {
	        return new SimpleDateFormat("YYYYMMddHHmmssSSS").format(new Date());
	    }
}
