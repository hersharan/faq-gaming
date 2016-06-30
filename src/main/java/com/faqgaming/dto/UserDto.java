package com.faqgaming.dto;

import java.io.Serializable;
import java.util.Date;

import com.faqgaming.domain.enums.Role;
import com.faqgaming.domain.enums.Status;

public class UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1122570778052486459L;
	
	private String email;

	private Role role;

	private Status status;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private String gender;

	private String profilePicUrl;

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
	
	

}
