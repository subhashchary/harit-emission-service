package com.threecortex.harit.haritemissionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.RiskEvalPreprocess;
import com.threecortex.harit.haritemissionservice.model.RiskEvalPreprocessId;

@Repository
public interface RiskEvalPreprocessRepository extends JpaRepository<RiskEvalPreprocess, RiskEvalPreprocessId> {

}
