package com.threecortex.harit.haritemissionservice.dto;

import java.util.List;

public class RiskEvalCategoryResponseDTO {

	private String riskCategoryName;
	private Integer weight;
	private Double scoreSelfData;
	private Double scorePublicData;
	private String additionalInfo;
	
	
	private List<RiskEvalSubCategoryResponseDTO> subCategoryDTO;
	
	public String getRiskCategoryName() {
		return riskCategoryName;
	}
	public void setRiskCategoryName(String riskCategoryName) {
		this.riskCategoryName = riskCategoryName;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Double getScoreSelfData() {
		return scoreSelfData;
	}
	public void setScoreSelfData(Double scoreSelfData) {
		this.scoreSelfData = scoreSelfData;
	}
	public Double getScorePublicData() {
		return scorePublicData;
	}
	public void setScorePublicData(Double scorePublicData) {
		this.scorePublicData = scorePublicData;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public List<RiskEvalSubCategoryResponseDTO> getSubCategoryDTO() {
		return subCategoryDTO;
	}
	public void setSubCategoryDTO(List<RiskEvalSubCategoryResponseDTO> subCategoryDTO) {
		this.subCategoryDTO = subCategoryDTO;
	}
	
	
}
