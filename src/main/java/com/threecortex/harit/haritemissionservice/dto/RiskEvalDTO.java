package com.threecortex.harit.haritemissionservice.dto;


public class RiskEvalDTO {

	private Long entityId;
	private Long evalSetId;
	private Long entityIngestionId;
	private Long templateId;
	private Long riskCategoryId;
	private Long subCategoryId;
	private Long selfPublic;
	private Long runId;
	private int paramWeight;
	private Double score;
	
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	public Long getEvalSetId() {
		return evalSetId;
	}
	public void setEvalSetId(Long evalSetId) {
		this.evalSetId = evalSetId;
	}
	public Long getEntityIngestionId() {
		return entityIngestionId;
	}
	public void setEntityIngestionId(Long entityIngestionId) {
		this.entityIngestionId = entityIngestionId;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public Long getRiskCategoryId() {
		return riskCategoryId;
	}
	public void setRiskCategoryId(Long riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public Long getSelfPublic() {
		return selfPublic;
	}
	public void setSelfPublic(Long selfPublic) {
		this.selfPublic = selfPublic;
	}
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public int getParamWeight() {
		return paramWeight;
	}
	public void setParamWeight(int paramWeight) {
		this.paramWeight = paramWeight;
	}
	
	
}
