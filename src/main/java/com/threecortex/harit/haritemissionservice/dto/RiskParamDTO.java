package com.threecortex.harit.haritemissionservice.dto;

import com.threecortex.harit.haritemissionservice.model.RiskParamMaster;

public class RiskParamDTO {
	
	private RiskParamMaster riskParamMaster;
	private LoggedInUserDTO loggedInUserDTO;
	public RiskParamMaster getRiskParamMaster() {
		return riskParamMaster;
	}
	public void setRiskParamMaster(RiskParamMaster riskParamMaster) {
		this.riskParamMaster = riskParamMaster;
	}
	public LoggedInUserDTO getLoggedInUserDTO() {
		return loggedInUserDTO;
	}
	public void setLoggedInUserDTO(LoggedInUserDTO loggedInUserDTO) {
		this.loggedInUserDTO = loggedInUserDTO;
	}
}
