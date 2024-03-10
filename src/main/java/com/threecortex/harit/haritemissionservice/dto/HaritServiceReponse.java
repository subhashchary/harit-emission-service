/**
 * 
 */
package com.threecortex.harit.haritemissionservice.dto;

/**
 * 
 */
public class HaritServiceReponse {
	
	public HaritServiceReponse() {
		
	}
	
	public HaritServiceReponse(String responseStatus, String responseMessage){
		this.responseStatus = responseStatus;
		this.responseMessage = responseMessage;
	}
	
	public HaritServiceReponse(String responseStatus){
		this.responseStatus = responseStatus;

	}
	private String responseStatus;
	private String responseMessage;
	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
