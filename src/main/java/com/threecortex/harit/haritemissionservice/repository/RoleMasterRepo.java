/**
 * 
 */
package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.RoleMaster;

/**
 * 
 */

@Repository
public interface RoleMasterRepo extends JpaRepository<RoleMaster, Long>, JpaSpecificationExecutor<RoleMaster>{

	List<RoleMaster> findByOrderByRoleIdAsc();

}
