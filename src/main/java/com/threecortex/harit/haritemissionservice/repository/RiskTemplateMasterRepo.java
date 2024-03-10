/**
 * 
 */
package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.RiskParamMaster;
import com.threecortex.harit.haritemissionservice.model.RiskTemplateMaster;

/**
 * 
 */
@Repository
public interface RiskTemplateMasterRepo extends JpaRepository<RiskTemplateMaster, Long>, JpaSpecificationExecutor<RiskTemplateMaster>{

	List<RiskTemplateMaster> findByOrderByTemplateId();

	RiskParamMaster findByTemplateId(Long riskTemplateId);

}
