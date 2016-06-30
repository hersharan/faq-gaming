package com.faqgaming.service;

import com.faqgaming.domain.User;
import com.faqgaming.dto.RegistrationDto;
import com.faqgaming.exception.FaqGamingException;

public interface UserService extends GenericService<User> {
	
	User register(RegistrationDto registrationDto) throws FaqGamingException;

}
