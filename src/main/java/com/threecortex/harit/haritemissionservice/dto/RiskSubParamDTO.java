package com.threecortex.harit.haritemissionservice.dto;

import com.threecortex.harit.haritemissionservice.model.SubRiskParamMaster;

public class RiskSubParamDTO {
	
	private SubRiskParamMaster subRiskParamMaster;
	private LoggedInUserDTO loggedInUserDTO;
	
	public SubRiskParamMaster getSubRiskParamMaster() {
		return subRiskParamMaster;
	}
	public void setSubRiskParamMaster(SubRiskParamMaster subRiskParamMaster) {
		this.subRiskParamMaster = subRiskParamMaster;
	}
	public LoggedInUserDTO getLoggedInUserDTO() {
		return loggedInUserDTO;
	}
	public void setLoggedInUserDTO(LoggedInUserDTO loggedInUserDTO) {
		this.loggedInUserDTO = loggedInUserDTO;
	}
}
