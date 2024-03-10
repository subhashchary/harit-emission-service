/**
 * 
 */
package com.threecortex.harit.haritemissionservice.dto;

import java.util.List;

import com.threecortex.harit.haritemissionservice.model.RiskTemplateComposition;
import com.threecortex.harit.haritemissionservice.model.RiskTemplateMaster;

/**
 * 
 */
public class RiskTemplateDTO {
	
	private RiskTemplateMaster riskTemplateMaster;
	private List<RiskTemplateComposition> riskTemplateCompositionList;
	private LoggedInUserDTO loggedInUserDTO;
	
	public RiskTemplateMaster getRiskTemplateMaster() {
		return riskTemplateMaster;
	}
	
	public void setRiskTemplateMaster(RiskTemplateMaster riskTemplateMaster) {
		this.riskTemplateMaster = riskTemplateMaster;
	}
	
	public List<RiskTemplateComposition> getRiskTemplateCompositionList() {
		return riskTemplateCompositionList;
	}
	
	public void setRiskTemplateCompositionList(List<RiskTemplateComposition> riskTemplateCompositionList) {
		this.riskTemplateCompositionList = riskTemplateCompositionList;
	}

	public LoggedInUserDTO getLoggedInUserDTO() {
		return loggedInUserDTO;
	}

	public void setLoggedInUserDTO(LoggedInUserDTO loggedInUserDTO) {
		this.loggedInUserDTO = loggedInUserDTO;
	}
}
