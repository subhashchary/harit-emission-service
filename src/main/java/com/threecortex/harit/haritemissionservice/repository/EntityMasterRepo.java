/**
 * 
 */
package com.threecortex.harit.haritemissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.EntityMaster;

/**
 * 
 */
@Repository
public interface EntityMasterRepo extends JpaRepository<EntityMaster, Long>, JpaSpecificationExecutor<EntityMaster>{

	EntityMaster findByLoginName(String loginName);

	List<EntityMaster> findByOrderByEntityId();

	EntityMaster findByEntityId(Long entityId);

	List<EntityMaster> findByOrderByEntityIdDesc();

}
