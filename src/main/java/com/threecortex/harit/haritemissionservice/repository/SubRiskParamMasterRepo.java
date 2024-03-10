/**
 * 
 */
package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.SubRiskParamMaster;

/**
 * 
 */
@Repository
public interface SubRiskParamMasterRepo extends JpaRepository<SubRiskParamMaster, Long>, JpaSpecificationExecutor<SubRiskParamMaster>{

	List<SubRiskParamMaster> findByRiskParamMasterRiskCategoryIdOrderBySubCategoryId(Long riskCategoryId);

}
