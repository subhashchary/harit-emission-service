package com.threecortex.harit.haritemissionservice.dto;

public class RiskEvalSubCategoryResponseDTO {

	private String riskSubCategoryName;
	private Integer weight;
	private Double scoreSelfData;
	private Double scorePublicData;
	private String additionalInfo;
	public String getRiskSubCategoryName() {
		return riskSubCategoryName;
	}
	public void setRiskSubCategoryName(String riskSubCategoryName) {
		this.riskSubCategoryName = riskSubCategoryName;
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
	
	
}
