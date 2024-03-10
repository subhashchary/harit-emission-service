/**
 * 
 */
package com.threecortex.harit.haritemissionservice.dto;


/**
 * 
 */
public class ValidateEntityRequest {

	private String username;
	
	public ValidateEntityRequest(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
