package com.outbound.call;

import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class OutboundCall {
	//public static final String ACCOUNT_SID = "AC2c03928dcbf33fca376102fcd73d47fc";
	//public static final String AUTH_TOKEN = "a6e4c39e904e6a5e8075b1e8509e06eb";
	
	public static final String ACCOUNT_SID = "ACd3d7a4618ef04112470d71e2ba9c78fd";
	
	public static final String AUTH_TOKEN = "743b839e3e658e1ccc3beeadd30f0d9f";

	public static void notifyEmergencyTeam() throws URISyntaxException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		//14243737413
		//13092760177
//12013791102
		Call call = Call.creator(new PhoneNumber("919059444485"),
				new PhoneNumber("+14243737413"),
				new URI("http://0a406c4b.ngrok.io/LifeSaviorDemo-Checkpoint_3/voice.xml")).create();

		
		MessageCreator msg = Message.creator(new PhoneNumber("+919059444485"),
				new PhoneNumber("+14243737413"),
				"Emergency incident reported for Naga Jyothi. Please respond immediately.");
		msg.create();

		
	}

}
