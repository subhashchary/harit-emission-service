/**
 * 
 */
package com.threecortex.harit.haritemissionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.threecortex.harit.haritemissionservice.model.MapEntityWithTemplate;

/**
 * 
 */
@Repository
public interface MapEntityWithTemplateRepo extends JpaRepository<MapEntityWithTemplate, Long>,  JpaSpecificationExecutor<MapEntityWithTemplate>{

	MapEntityWithTemplate findByTemplateId(Long riskTemplateId);

	MapEntityWithTemplate findByEntityId(Long entityId);

}
