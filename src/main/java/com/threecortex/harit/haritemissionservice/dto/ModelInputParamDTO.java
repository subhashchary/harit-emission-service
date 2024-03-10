package com.threecortex.harit.haritemissionservice.dto;

public class ModelInputParamDTO {

	private Long entityId;
	private Long evalSetId;
	private Long entityIngestionId;
	private Long templateId;
	
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
	
	
}
