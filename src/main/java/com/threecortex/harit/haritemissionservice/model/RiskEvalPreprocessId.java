package com.threecortex.harit.haritemissionservice.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class RiskEvalPreprocessId implements Serializable {

	private Long entityId;
	private Long evalSetId;
	private Long entityIngestionId;
	private Long templateId;
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
