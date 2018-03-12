package com.email;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.excel.ReadExcel;
import com.outbound.call.OutboundCall;
import com.pojo.UserProfileDetails;

public class SendEmail {
	final static Logger logger = Logger.getLogger(SendEmail.class.getName());
	public String sendEmailTo108(String filePath) throws IOException, URISyntaxException {

		logger.info("Emergency team is being contacted.");
		final String toUsername = "108.medicalemergency@gmail.com";
		final String fromUsername = "lifesavior.service@gmail.com";
		final String password = "jyothi1979";
		String result ="failure";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromUsername, password);
					}
				});

		try {
			String mobileNumber = "9989932764";
			Message message = new MimeMessage(session);
			//message.setFrom(new InternetAddress(toUsername));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toUsername));
			ReadExcel readExcel = new ReadExcel();
			
			UserProfileDetails userProfileDetails = readExcel.readExcelData(mobileNumber, filePath);
			
			message.setSubject("Incident Notification for " + userProfileDetails.getFirstName() + " "+ userProfileDetails.getLastName());
			message.setText("Request your immediate assistance for below user:" +
			"\n" +
					"\n" + "First Name :" + userProfileDetails.getFirstName()+
					"\n" + "Last Name :" + userProfileDetails.getLastName()+
					"\n" + "Mobile Number :" + userProfileDetails.getMobileNum()+
					"\n" + "Age :" + userProfileDetails.getDob() +
					"\n" + "Critical illness as per the record:" + userProfileDetails.getCriticalIllness()+
					"\n" + "\n" +
					"\n" ); //TODO url

			Transport.send(message);
			
			OutboundCall.notifyEmergencyTeam();
			result =  "success";
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		logger.info("Emergency team is updated with incident information in Email and SMS.");
		logger.info("Emergency family contacts are also updated about the incident.");
		return result;
	}

}
