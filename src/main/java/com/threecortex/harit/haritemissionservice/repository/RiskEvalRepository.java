package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.RiskEval;
import com.threecortex.harit.haritemissionservice.model.RiskEvalId;

@Repository
public interface RiskEvalRepository extends JpaRepository<RiskEval, RiskEvalId> {

	public List<RiskEval> findByRiskEvalIdRunId(Long runId);
}
