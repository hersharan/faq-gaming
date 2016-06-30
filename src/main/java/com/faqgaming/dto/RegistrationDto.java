package com.faqgaming.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class RegistrationDto {
	
	@NotBlank(message = "Should not be left blank")
	@Length(min = 3,max = 15, message = "The field must be in between 3-15 chars")
	private String firstName;
	
	@NotBlank(message = "Should not be left blank")
	@Length(min = 3,max = 15, message = "The field must be  in between 3-15 chars")
	private String lastName;
	
	@NotBlank(message = "Should not be left blank")
	@Email(message="Please provide a valid email address")
	private String email;
	
	@NotBlank(message = "Should not be left blank")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegistrationDto [firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password
				+ "]";
	}
	
}
