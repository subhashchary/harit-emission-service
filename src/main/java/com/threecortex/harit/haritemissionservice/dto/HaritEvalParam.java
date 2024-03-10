package com.threecortex.harit.haritemissionservice.dto;

public class HaritEvalParam {

	private Long entityId;
	private Long evalSetId;
	private Long entityIngestionId;
	private Long templateId;
	private Long categoryId;
	private Long subCategoryId;
	private String subCategoryFileName;
	
	private String filePath;
	private String fileOutPath;
	private String subCategoryFileOutPath;
	
	private Long compositionId;
	
	private int paramWeight;
	private Long runId;
	
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public String getSubCategoryFileName() {
		return subCategoryFileName;
	}
	public void setSubCategoryFileName(String subCategoryFileName) {
		this.subCategoryFileName = subCategoryFileName;
	}
	public String getFileOutPath() {
		return fileOutPath;
	}
	public void setFileOutPath(String fileOutPath) {
		this.fileOutPath = fileOutPath;
	}
	public String getSubCategoryFileOutPath() {
		return subCategoryFileOutPath;
	}
	public void setSubCategoryFileOutPath(String subCategoryFileOutPath) {
		this.subCategoryFileOutPath = subCategoryFileOutPath;
	}
	public Long getCompositionId() {
		return compositionId;
	}
	public void setCompositionId(Long compositionId) {
		this.compositionId = compositionId;
	}
	public int getParamWeight() {
		return paramWeight;
	}
	public void setParamWeight(int paramWeight) {
		this.paramWeight = paramWeight;
	}
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	
	
	
	
}
