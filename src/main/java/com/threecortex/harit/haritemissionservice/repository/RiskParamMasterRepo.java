/**
 * 
 */
package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.RiskParamMaster;

/**
 * 
 */
@Repository
public interface RiskParamMasterRepo
		extends JpaRepository<RiskParamMaster, Long>, JpaSpecificationExecutor<RiskParamMaster> {

	List<RiskParamMaster> findByOrderByRiskCategoryId();

	public RiskParamMaster findByRiskCategoryId(Long riskCategoryId);

}
