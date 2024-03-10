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
/**
 * 
 */
import jakarta.persistence.UniqueConstraint;
@Entity
@DynamicUpdate
@Where(clause = "is_deleted = false and oc_status = 'O'")
@SQLDelete(sql = "update RiskTemplateComposition set oc_status = 'C' where composition_id = ?")
@Table(name = "TB_MST_RISK_TEMPLATE_COMPOSITION", uniqueConstraints = { @UniqueConstraint(name = "idx_uq_risk_templt_comp",  columnNames = {"template_id", "risk_category_id", "sub_category_id"}) })
public class RiskTemplateComposition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "composition_id")
	private Long compositionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", referencedColumnName = "template_id", nullable = false)
	private RiskTemplateMaster riskTemplateMaster;
	
	@Column(name = "risk_category_id", nullable = false)
	private Long riskCategoryId;
	
	@Column(name = "sub_category_id")
	private Long subCategoryId;
	
	@Column(name = "param_weight", nullable = false)
	private Integer paramWeight;
	
	//min and max value is used for evaluation criteria on what is expected range
	//Grading value ranges are maintained in Lookup params
	@Column(name = "min_value", nullable = false)
	private Double minValue;
	
	@Column(name = "max_value", nullable = false)
	private Double maxValue;
	
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

	public Integer getParamWeight() {
		return paramWeight;
	}

	public void setParamWeight(Integer paramWeight) {
		this.paramWeight = paramWeight;
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

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public RiskTemplateMaster getRiskTemplateMaster() {
		return riskTemplateMaster;
	}

	public void setRiskTemplateMaster(RiskTemplateMaster riskTemplateMaster) {
		this.riskTemplateMaster = riskTemplateMaster;
	}

}
