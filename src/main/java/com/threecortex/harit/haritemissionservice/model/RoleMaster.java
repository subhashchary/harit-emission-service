/**
 * 
 */
package com.threecortex.harit.haritemissionservice.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 */
@Entity
@DynamicUpdate
@Where(clause = "is_deleted = false and oc_status = 'O'")
@SQLDelete(sql = "update RoleMaster set oc_status = 'C' where role_id = ?")
@Table(name = "tb_mst_role", uniqueConstraints={
        @UniqueConstraint( name = "uk_mst_role",  columnNames ={"role_name"})
     })
@JsonInclude(Include.NON_EMPTY)
public class RoleMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", length = 11)
	private Long roleId;
	
	@Column(name = "role_name", length = 32, nullable = false)
	private String roleName;

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	
}
