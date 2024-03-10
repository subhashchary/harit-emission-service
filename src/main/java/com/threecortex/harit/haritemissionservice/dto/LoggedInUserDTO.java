/**
 * 
 */
package com.threecortex.harit.haritemissionservice.dto;

/**
 * 
 */
public class LoggedInUserDTO {
	
	Long loggedInUserId;
	String loginName;
	Long roleId;
	public Long getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(Long loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
