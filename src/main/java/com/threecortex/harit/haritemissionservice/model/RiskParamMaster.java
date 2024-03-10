/**
 * 
 */
package com.threecortex.harit.haritemissionservice.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * 
 */
@Entity
@DynamicUpdate
@Where(clause = "is_deleted = false and oc_status = 'O'")
@SQLDelete(sql = "update RiskParamMaster set oc_status = 'C' where risk_category_id = ?")
@Table(name = "TB_MST_RISK_PARAM")
public class RiskParamMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "risk_category_id")
	private Long riskCategoryId;

	@Column(name = "param_name", length = 32, nullable = false, unique = true)
	private String paramName;
	
	@Column(name = "tool_tip", length = 64)
	private String toolTip;

	@Column(name = "created_by", length = 11, nullable = false)
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

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

	public Long getRiskCategoryId() {
		return riskCategoryId;
	}

	public void setRiskCategoryId(Long riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
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

	public String getToolTip() {
		return toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
}
