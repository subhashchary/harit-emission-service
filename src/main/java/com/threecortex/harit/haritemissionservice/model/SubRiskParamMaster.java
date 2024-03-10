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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * 
 */
@Entity
@DynamicUpdate
@Where(clause = "is_deleted = false and oc_status = 'O'")
@SQLDelete(sql = "update SubRiskParamMaster set oc_status = 'C' where sub_category_id = ?")
@Table(name = "TB_MST_RISK_SUB_PARAM", uniqueConstraints={
        @UniqueConstraint( name = "uk_mst_risk_sub_param",  columnNames ={"sub_category_name","risk_category_id"})
     })
public class SubRiskParamMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_category_id")
	private Long subCategoryId;
	
	@Column(name = "sub_category_name", length = 32, nullable = false)
	private String subCategoryName;
	
	@Column(name = "tool_tip", length = 64)
	private String toolTip;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "risk_category_id", referencedColumnName = "risk_category_id", nullable = false)
	private RiskParamMaster riskParamMaster;
	
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

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public RiskParamMaster getRiskParamMaster() {
		return riskParamMaster;
	}

	public void setRiskParamMaster(RiskParamMaster riskParamMaster) {
		this.riskParamMaster = riskParamMaster;
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
