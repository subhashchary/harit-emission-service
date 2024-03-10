package com.threecortex.harit.haritemissionservice.model;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_RISK_EVAL_PREPROCESS")
public class RiskEvalPreprocess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eval_process_id")
	private Long evalProcessId;
	private Long runId;
	private Long entityId;
	private Long evalSetId;
	private Long entityIngestionId;
	private Long templateId;
	private Long selfPublic;
	
	
	private Date runDate;
	
	private String fileOutPath;
	
	@Column(name = "verified_by", length = 11, nullable = false)
	private Integer verifiedBy;
	
	@Column(name = "verified_date")
	private Timestamp verifiedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private Timestamp updatedDate;
	
	@Column(name = "oc_status", length = 1, nullable = false)
	@ColumnDefault("'O'")
	private String ocStatus;
	
	@Column(name = "is_deleted", nullable = false)
	@ColumnDefault("false")
	private boolean isDeleted;

	

	public Date getRunDate() {
		return runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public String getFileOutPath() {
		return fileOutPath;
	}

	public void setFileOutPath(String fileOutPath) {
		this.fileOutPath = fileOutPath;
	}

	public Integer getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(Integer verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Timestamp getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(Timestamp verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getOcStatus() {
		return ocStatus;
	}

	public void setOcStatus(String ocStatus) {
		this.ocStatus = ocStatus;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getEvalProcessId() {
		return evalProcessId;
	}

	public void setEvalProcessId(Long evalProcessId) {
		this.evalProcessId = evalProcessId;
	}

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
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

	public Long getSelfPublic() {
		return selfPublic;
	}

	public void setSelfPublic(Long selfPublic) {
		this.selfPublic = selfPublic;
	} 

	
}
