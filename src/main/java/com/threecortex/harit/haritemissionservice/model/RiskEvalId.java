package com.threecortex.harit.haritemissionservice.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Embeddable;

@Embeddable
public class RiskEvalId implements Serializable {
private Long entityId;
private Long evalSetId;
private Long entityIngestionId;
private Long templateId;
private Long compositionId;
private Long riskCategoryId;
private Long subCategoryId;
private Long selfPublic;
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
public Long getTemplateId() {
	return templateId;
}
public void setTemplateId(Long templateId) {
	this.templateId = templateId;
}
public Long getCompositionId() {
	return compositionId;
}
public void setCompositionId(Long compositionId) {
	this.compositionId = compositionId;
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



}
