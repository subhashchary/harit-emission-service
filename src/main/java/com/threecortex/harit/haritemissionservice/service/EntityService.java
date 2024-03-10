/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.dto.EntityDTO;
import com.threecortex.harit.haritemissionservice.dto.HaritServiceReponse;
import com.threecortex.harit.haritemissionservice.dto.LoggedInUserDTO;
import com.threecortex.harit.haritemissionservice.model.EntityMaster;

/**
 * 
 */
@Service
public interface EntityService {

	HaritServiceReponse createEnity(EntityDTO createEntityDTO);

	EntityMaster getEntityById(Long entityId);

	EntityMaster getByLoginName(String loginName);

	HaritServiceReponse closeUser(Long entityId, LoggedInUserDTO loggedInUserDetailsDTO);

	List<EntityMaster> fetchEntities();

	List<EntityMaster> fetchUnMappedEntities();

}
