package com.faqgaming.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faqgaming.domain.enums.Role;
import com.faqgaming.domain.enums.Status;

@Document
public class User extends AbstractMongoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3038077805379501237L;

	private List<String> invalidTokens = new ArrayList<String>();

	@Indexed(unique = true)
	@NotBlank(message = "Email can't be blank")
	private String email;

	private Role role;

	private Status status;

	private String password;

	private String otp;

	@NotBlank(message = "first name cannot be blank")
	private String firstName;

	@NotBlank(message = "last name cannot be blank")
	private String lastName;

	private Date dateOfBirth;

	private String gender;

	private String profilePicUrl;

	public List<String> getInvalidTokens() {
		return invalidTokens;
	}

	public void setInvalidTokens(List<String> invalidTokens) {
		this.invalidTokens = invalidTokens;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	@Override
	public String toString() {
		return "User [invalidTokens=" + invalidTokens + ", email=" + email
				+ ", role=" + role + ", status=" + status + ", password="
				+ password + ", otp=" + otp + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", profilePicUrl=" + profilePicUrl
				+ "]";
	}
	
	
}
