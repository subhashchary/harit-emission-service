/**
 * 
 */
package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.LookupParams;

/**
 * 
 */
@Repository
public interface LookupParamsRepo extends JpaRepository<LookupParams, Long>, JpaSpecificationExecutor<LookupParams>{

	List<LookupParams> findByOrderByLookupIdAsc();

	List<LookupParams> findByLookupParamOrderByLookupIdAsc(String paramName);

}
