/**
 * 
 */
package com.threecortex.harit.haritemissionservice.dto;

import java.io.Serializable;

import org.springframework.lang.NonNull;

/**
 * 
 */
public class EmailObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4383250076874591139L;
	
	public EmailObject(String to, String subject, String emailTextBody) {
		this.to = to;
		this.subject = subject;
		this.emailTextBody = emailTextBody;
	}
	
	@NonNull
	private String to;
	@NonNull
	private String subject;
	@NonNull
	private String emailTextBody;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailTextBody() {
		return emailTextBody;
	}
	public void setEmailTextBody(String emailTextBody) {
		this.emailTextBody = emailTextBody;
	}
}
