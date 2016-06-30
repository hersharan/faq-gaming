package com.faqgaming.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.faqgaming.dto.RegistrationDto;
import com.faqgaming.dto.UserDto;
import com.faqgaming.exception.FaqGamingException;
import com.faqgaming.service.UserService;
import com.faqgaming.transformer.UserTransformer;
import com.faqgaming.util.Constants;
import com.faqgaming.util.RestResponse;
import com.faqgaming.util.RestUtils;

@RestController
@RequestMapping(value = Constants.API_PREFIX + "auth")
public class LoginController {

//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private UserTransformer userTransformer;

	/**
	 * Method for registering user 
	 * while registering user,firstly provide a access code to user then save the new user in database after that send a activation email to user.
	 * @param registrationDto: basic details for registering user.
	 * @return: required details of registered user.
	 * @throws FaqGamingException: if occur any exception while registering a user.
	 */
//	@RequestMapping(value = { "/register", "/register/" }, method = RequestMethod.POST)
//	public ResponseEntity<RestResponse<UserDto>> registerUser(
//			@RequestBody @Valid RegistrationDto registrationDto)
//			throws FaqGamingException {
//		return RestUtils.successResponse(userTransformer.toDto(userService
//				.register(registrationDto)));
//	}
}
