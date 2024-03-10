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
@Table(name="TB_RISK_EVALUATION_DETAIL")
public class RiskEvaluationDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statement_id")
	private Long statementId;
	private Long entityId;
	private Long evalSetId;
	private Long entityIngestionId;
	private Long templateId;
	private Long compositionId;
	private Long riskCategoryId;
	private Long subCategoryId;
	private Long selfPublic;
	private Long runId;
	private Date runDate;
	private int paramWeight;
	private double  postiveScore;
	private double  neutralScore;
	private double negativeScore;
	private double bestScore;
	private String statement;
	private double positiveWeightage;
	private double neutralWeightage;
	private double negativeWeightage;
	private double totalWeightage;

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

	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
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

	public Date getRunDate() {
		return runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public int getParamWeight() {
		return paramWeight;
	}

	public void setParamWeight(int paramWeight) {
		this.paramWeight = paramWeight;
	}

	public double getPostiveScore() {
		return postiveScore;
	}

	public void setPostiveScore(double postiveScore) {
		this.postiveScore = postiveScore;
	}

	public double getNeutralScore() {
		return neutralScore;
	}

	public void setNeutralScore(double neutralScore) {
		this.neutralScore = neutralScore;
	}

	public double getNegativeScore() {
		return negativeScore;
	}

	public void setNegativeScore(double negativeScore) {
		this.negativeScore = negativeScore;
	}

	public double getBestScore() {
		return bestScore;
	}

	public void setBestScore(double bestScore) {
		this.bestScore = bestScore;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	
	public double getPositiveWeightage() {
		return positiveWeightage;
	}

	public void setPositiveWeightage(double positiveWeightage) {
		this.positiveWeightage = positiveWeightage;
	}

	public double getNeutralWeightage() {
		return neutralWeightage;
	}

	public void setNeutralWeightage(double neutralWeightage) {
		this.neutralWeightage = neutralWeightage;
	}

	public double getNegativeWeightage() {
		return negativeWeightage;
	}

	public void setNegativeWeightage(double negativeWeightage) {
		this.negativeWeightage = negativeWeightage;
	}

	
	public double getTotalWeightage() {
		return totalWeightage;
	}

	public void setTotalWeightage(double totalWeightage) {
		this.totalWeightage = totalWeightage;
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
	
	
}
