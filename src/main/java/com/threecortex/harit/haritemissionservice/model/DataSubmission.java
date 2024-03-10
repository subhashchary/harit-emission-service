package com.threecortex.harit.haritemissionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_MST_DATA_SUBMISSION")
public class DataSubmission {

	@Id
	private Long dataSubmissionId;
	private String fileName ;
	private Long entityIngestionId;
	public Long getDataSubmissionId() {
		return dataSubmissionId;
	}
	public void setDataSubmissionId(Long dataSubmissionId) {
		this.dataSubmissionId = dataSubmissionId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getEntityIngestionId() {
		return entityIngestionId;
	}
	public void setEntityIngestionId(Long entityIngestionId) {
		this.entityIngestionId = entityIngestionId;
	}
	
	
	
}
