package com.faqgaming.transformer;

import com.faqgaming.domain.User;
import com.faqgaming.dto.UserDto;

public class UserTransformer extends GenericTransformer<User, UserDto> {

	@Override
	public UserDto toDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setGender(user.getGender());
		userDto.setProfilePicUrl(user.getProfilePicUrl());
		userDto.setRole(user.getRole());
		userDto.setStatus(user.getStatus());
		return userDto;
	}

}
