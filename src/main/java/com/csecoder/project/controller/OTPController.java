package com.csecoder.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csecoder.project.helper.EmailTemplate;
import com.csecoder.project.service.EmailService;
import com.csecoder.project.service.OTPService;

@Controller
public class OTPController {

	@Autowired
	public OTPService otpService;

	@Autowired
	public EmailService emailService;

	@GetMapping("/generateOtp")
	public String generateOTP() throws MessagingException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		int otp = otpService.generateOTP(username);
		// Generate The Template to send OTP
		EmailTemplate template = new EmailTemplate("SendOtp.html");
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("user", username);
		replacements.put("OTPSEND", String.valueOf(otp));
		String message = template.getTemplate(replacements);

		emailService.sendOtpMessage("ujjawalkumar04@gmail.com", "OTP -CSECODER", message);

		return "otppage";
	}

	@RequestMapping(value = "/validateOtp", method = RequestMethod.GET)
	public @ResponseBody String validateOtp(@RequestParam("OTPSEND") int OTPSEND) {

		final String SUCCESS = "Congratulations Your Otp is valid";
		final String FAIL = "Entered Otp is NOT valid. Please Retry!";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		// Validate the Otp
		if (OTPSEND >= 0) {

			int serverOtp = otpService.getOtp(username);
			if (serverOtp > 0) {
				if (OTPSEND == serverOtp) {
					otpService.clearOTP(username);

					return (SUCCESS);
				} else {
					return FAIL;
				}
			} else {
				return FAIL;
			}
		} else {
			return FAIL;
		}
	}
}
