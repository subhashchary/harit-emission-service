package com.threecortex.harit.haritemissionservice.nlp.model.dto;

public class SentenseCategorizerDTO {

	public String sentenseCategoryName;
	public double categoryProbability;
	public String sentense;
	
	private Long entityId;
	private Long evalSetId;
	private Long entityIngestionId;
	private Long templateId;
	private Long categoryId;
	private Long subCategoryId;
	private Long compositionId;
	private Long runId;
	private String subCategoryFileOutPath;
	
	public String getSentenseCategoryName() {
		return sentenseCategoryName;
	}
	public void setSentenseCategoryName(String sentenseCategoryName) {
		this.sentenseCategoryName = sentenseCategoryName;
	}
	public double getCategoryProbability() {
		return categoryProbability;
	}
	public void setCategoryProbability(double categoryProbability) {
		this.categoryProbability = categoryProbability;
	}
	public String getSentense() {
		return sentense;
	}
	public void setSentense(String sentense) {
		this.sentense = sentense;
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
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public Long getCompositionId() {
		return compositionId;
	}
	public void setCompositionId(Long compositionId) {
		this.compositionId = compositionId;
	}
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public String getSubCategoryFileOutPath() {
		return subCategoryFileOutPath;
	}
	public void setSubCategoryFileOutPath(String subCategoryFileOutPath) {
		this.subCategoryFileOutPath = subCategoryFileOutPath;
	}
	
	
	
}
