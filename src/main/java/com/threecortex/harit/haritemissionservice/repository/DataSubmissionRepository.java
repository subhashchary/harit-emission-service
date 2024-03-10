package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.DataSubmission;

@Repository
public interface DataSubmissionRepository extends JpaRepository<DataSubmission, Long> {

	public List<DataSubmission> findByentityIngestionId(Long entityIngestionId);
}
