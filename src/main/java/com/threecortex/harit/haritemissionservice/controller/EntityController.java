/**
 * 
 */
package com.threecortex.harit.haritemissionservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.threecortex.harit.haritemissionservice.dto.EntityDTO;
import com.threecortex.harit.haritemissionservice.dto.HaritServiceReponse;
import com.threecortex.harit.haritemissionservice.model.EntityMaster;
import com.threecortex.harit.haritemissionservice.service.EntityService;



/**
 * 
 */
@RestController
@RequestMapping(path = "/entity", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntityController {
	
	private static final Logger logger = LoggerFactory.getLogger(EntityController.class);
	
	@Autowired
	private EntityService entityService;
	
	@PostMapping(path = "/onboard")
	public HaritServiceReponse createUser(@RequestBody EntityDTO createEntityDTO) {
		logger.info("Entity onboarding Called {}");
		return entityService.createEnity(createEntityDTO);
	}
	
	@GetMapping(path = "/byid/{entityId}")
	public EntityMaster getEntityMaster(@PathVariable Long entityId) {	
		logger.info("Fetching the Entity details by id: {}", entityId);
		return entityService.getEntityById(entityId);		
	}
	
	@GetMapping(path = "/getByLoginName")
	public EntityMaster getByLoginName(@RequestParam  String loginName) {
		
		logger.info("Fetching the entity details by loginname :::{}",loginName);
		return entityService.getByLoginName(loginName.toUpperCase());
		
	}

}
