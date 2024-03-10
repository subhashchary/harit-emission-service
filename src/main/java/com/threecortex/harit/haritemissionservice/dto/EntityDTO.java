/**
 * 
 */
package com.threecortex.harit.haritemissionservice.dto;

import com.threecortex.harit.haritemissionservice.model.EntityMaster;

/**
 * 
 */
public class EntityDTO {
	
	
	private EntityMaster entityMaster;
	private LoggedInUserDTO loggedInUserDTO;
	public EntityMaster getEntityMaster() {
		return entityMaster;
	}
	public void setEntityMaster(EntityMaster entityMaster) {
		this.entityMaster = entityMaster;
	}
	public LoggedInUserDTO getLoggedInUserDTO() {
		return loggedInUserDTO;
	}
	public void setLoggedInUserDTO(LoggedInUserDTO loggedInUserDTO) {
		this.loggedInUserDTO = loggedInUserDTO;
	}
	
	

}
