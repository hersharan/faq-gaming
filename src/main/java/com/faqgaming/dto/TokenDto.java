package com.faqgaming.dto;

import com.faqgaming.domain.enums.Role;


public class TokenDto {

	  private String payload;

	  private Role role;

	  public TokenDto() {}

	  public TokenDto(String payload, Role role) {
	    super();
	    this.payload = payload;
	    this.role = role;
	  }

	  public String getPayload() {
	    return payload;
	  }

	  public void setPayload(String payload) {
	    this.payload = payload;
	  }

	  public Role getRole() {
	    return role;
	  }

	  public void setRole(Role role) {
	    this.role = role;
	  }

	  @Override
	  public String toString() {
	    return "TokenDto [payload=" + payload + ", role=" + role + "]";
	  }

	}
