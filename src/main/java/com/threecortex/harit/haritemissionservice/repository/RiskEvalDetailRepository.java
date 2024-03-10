package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.RiskEvaluationDetail;

@Repository
public interface RiskEvalDetailRepository extends JpaRepository<RiskEvaluationDetail, Long>,JpaSpecificationExecutor<RiskEvaluationDetail> {

	
	/*
	 * @Query(value =
	 * "select risk_category_id ,entity_id ,entity_ingestion_id ,eval_set_id ,template_id ,self_public ,run_id,SUM(total_weightage)from tb_risk_evaluation_detail where run_id =:runId group by risk_category_id ,entity_id,entity_ingestion_id ,eval_set_id ,template_id,self_public,run_id"
	 * , nativeQuery = true) public List<RiskEvalCustomQueryDTO>
	 * findGroupByData(Long runId);
	 */
	public List<RiskEvaluationDetail> findByEntityIdAndEvalSetIdAndEntityIngestionIdAndTemplateIdAndRunId(Long entityId,Long evalSetId,Long entityIngestionId,Long templateId,Long runId);
}
