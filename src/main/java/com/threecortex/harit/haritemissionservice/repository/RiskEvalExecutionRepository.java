package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.RiskEvalExecution;

@Repository
public interface RiskEvalExecutionRepository
		extends JpaRepository<RiskEvalExecution, Long>, JpaSpecificationExecutor<RiskEvalExecution> {

	public List<RiskEvalExecution> findByRunId(Long runID);

	public RiskEvalExecution findTopByEntityIdAndEvalSetIdAndEntityIngestionIdAndTemplateIdOrderByRunIdDesc(
			Long entityId, Long evalSetId, Long entityIngestionId, Long templateId);

}
