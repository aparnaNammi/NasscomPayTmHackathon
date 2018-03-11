package com.outbound.call;

import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class OutboundCall {
	public static final String ACCOUNT_SID = "AC2c03928dcbf33fca376102fcd73d47fc";
	public static final String AUTH_TOKEN = "a6e4c39e904e6a5e8075b1e8509e06eb";

	public static void notifyEmergencyTeam() throws URISyntaxException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Call call = Call.creator(new PhoneNumber("919490167336"),
				new PhoneNumber("+13092760177"),
				new URI("http://192.168.1.4:8080/examples/voice.xml")).create();

		
		MessageCreator msg = Message.creator(new PhoneNumber("+919490167336"),
				new PhoneNumber("+13092760177"),
				"Emergency incident reported for Naga Jyothi. Please respond immediately.");
		msg.create();

		
	}

}
