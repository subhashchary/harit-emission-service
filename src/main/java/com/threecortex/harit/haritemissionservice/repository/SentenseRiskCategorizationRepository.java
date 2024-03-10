package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.SentenseRiskCategorization;

@Repository
public interface SentenseRiskCategorizationRepository extends JpaRepository<SentenseRiskCategorization, Long> {

	public List<SentenseRiskCategorization> findByRunId(Long runId);
}
