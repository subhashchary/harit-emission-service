/**
 * 
 */
package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.RiskTemplateComposition;
import com.threecortex.harit.haritemissionservice.model.RiskTemplateMaster;

/**
 * 
 */
@Repository
public interface RiskTemplateCompositionRepo extends JpaRepository<RiskTemplateComposition, Long>, JpaSpecificationExecutor<RiskTemplateComposition> {

	List<RiskTemplateComposition> findByRiskTemplateMasterOrderByCompositionId(RiskTemplateMaster riskTemplateMaster);

	List<RiskTemplateComposition> findByRiskTemplateMasterTemplateIdOrderByCompositionId(Long riskTemplateId);

	

}
