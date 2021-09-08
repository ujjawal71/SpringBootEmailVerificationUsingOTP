package com.csecoder.project.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Value("${spring.mail.username}")
	private String fromCSECODER;

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendOtpMessage(String to, String subject, String message) throws MessagingException {
	
		 MimeMessage msg = javaMailSender.createMimeMessage();

	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	        helper.setTo(to);

	        helper.setSubject(subject);

	        helper.setText(message, true);
	        try {
				helper.setFrom(fromCSECODER,"CSECODER-OTP");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        javaMailSender.send(msg);
   }
	
}
	
