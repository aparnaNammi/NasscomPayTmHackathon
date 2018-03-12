package com.outbound.call;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lsa.util.LSLogger;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.Say;

@SuppressWarnings("serial")
@WebServlet("/voice")
public class IncomingCallServlet extends HttpServlet {
	
	final static Logger logger = Logger.getLogger(IncomingCallServlet.class.getName());
	// Handle HTTP POST to /voice
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String mobileNumber = "9989932764";
		VoiceResponse twiml = new VoiceResponse.Builder().say(
				new Say.Builder("You have reached Life savior service. Repeating. You have reached Life savior service. Our team would reach emergency services. Stay strong. Repeating. Our team would reach emergency services. Stay strong. ").voice(Say.Voice.ALICE).build())
				.build();
		
		response.setContentType("text/xml");

		try {
			response.getWriter().print(twiml.toXml());
		} catch (TwiMLException e) {
			e.printStackTrace();
		}
		logger.info("Incident notification received to Life Savior for :" + mobileNumber);
	}
}