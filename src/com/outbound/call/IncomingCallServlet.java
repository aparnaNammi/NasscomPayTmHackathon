package com.outbound.call;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.db.ConnectToDB;
import com.email.SendEmail;
import com.geolocation.CurlProgram;
import com.geolocation.LocationCoordinates;
import com.pojo.UserProfileDetails;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallReader;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

@SuppressWarnings("serial")
@WebServlet("/voice")
public class IncomingCallServlet extends HttpServlet {

	final static Logger logger = Logger.getLogger(IncomingCallServlet.class
			.getName());

	// Handle HTTP POST to /voice
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String accountSid = "AC2c03928dcbf33fca376102fcd73d47fc";
		String authToken = "a6e4c39e904e6a5e8075b1e8509e06eb";
		Twilio.init(accountSid, authToken);

		// CallFetcher call = Call.fetcher("AC2c03928dcbf33fca376102fcd73d47fc",
		// "a6e4c39e904e6a5e8075b1e8509e06eb");

		CallReader call = Call.reader(accountSid);
		String mobileNumber = "";
		ResourceSet<Call> resSet = call.read();
		for (Call call1 : resSet) {
			mobileNumber = call1.getFrom();
			mobileNumber.replace("+191","");
			break;
		}

		VoiceResponse twiml = new VoiceResponse.Builder()
				.say(new Say.Builder(
						"You have reached Life savior service. Repeating. You have reached Life savior service. Our team would reach emergency services. Stay strong. Repeating. Our team would reach emergency services. Stay strong. ")
						.voice(Say.Voice.ALICE).build()).build();

		response.setContentType("text/xml");

		try {
			response.getWriter().print(twiml.toXml());
			System.out.println(twiml.toXml());
		} catch (TwiMLException e) {
			e.printStackTrace();
		}
		logger.info("Incident notification received to Life Savior for :"
				+ mobileNumber);

		processFlow(mobileNumber);
		
	}

	private void processFlow(String mobileNumber) throws IOException {
		ConnectToDB connectToDB = new ConnectToDB();
		try {
			UserProfileDetails userProfileDetails = connectToDB.selectData(mobileNumber);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Static map for handling Cell location tracking
		Map<String, LocationCoordinates> locationCoorMap = new HashMap<>();
		LocationCoordinates locCoor = new LocationCoordinates();
		locCoor.setCellId(62573);
		locCoor.setLac(2701);
		locCoor.setMcc(404);
		locCoor.setMnc(73);
		locationCoorMap.put("9490167336", locCoor);
		
		LocationCoordinates locCoor1 = new LocationCoordinates();
		locCoor1.setCellId(5803);
		locCoor1.setLac(20040);
		locCoor1.setMcc(404);
		locCoor1.setMnc(13);
		locationCoorMap.put("8886860855", locCoor1);
		
		
		//Dint work
		LocationCoordinates locCoor2 = new LocationCoordinates();
		locCoor2.setCellId(5750);
		locCoor2.setLac(20);
		locCoor2.setMcc(405);
		locCoor2.setMnc(854);
		locationCoorMap.put("8886860857", locCoor2);
		
		LocationCoordinates locCoor3 = new LocationCoordinates();
		locCoor3.setCellId(16428);
		locCoor3.setLac(36086);
		locCoor3.setMcc(404);
		locCoor3.setMnc(07);
		locationCoorMap.put("8886860858", locCoor3);
		
		LocationCoordinates locCoor4 = new LocationCoordinates();
		locCoor4.setCellId(43751);
		locCoor4.setLac(114);
		locCoor4.setMcc(404);
		locCoor4.setMnc(04);
		locationCoorMap.put("9989932764", locCoor4);
		
		
		
		CurlProgram curlProg = new CurlProgram();
		String destinationFile = curlProg.geolocaton(locationCoorMap.get(mobileNumber));
		
		
		SendEmail sendEmail = new SendEmail();
		try {
			sendEmail.sendEmailTo108(mobileNumber, destinationFile);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[]) throws IOException{
		IncomingCallServlet servlet = new IncomingCallServlet();
		servlet.processFlow("9989932764");
	}
}