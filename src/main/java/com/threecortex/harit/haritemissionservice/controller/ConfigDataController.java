/**
 * 
 */
package com.threecortex.harit.haritemissionservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threecortex.harit.haritemissionservice.common.HaritContstants;
import com.threecortex.harit.haritemissionservice.model.LookupParams;
import com.threecortex.harit.haritemissionservice.model.RoleMaster;
import com.threecortex.harit.haritemissionservice.service.ConfigDataService;



/**
 * 
 */
@RestController
@RequestMapping(path = "/config", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConfigDataController {
	private static final Logger logger = LoggerFactory.getLogger(ConfigDataController.class);
	
	@Autowired
	private ConfigDataService configDataService;
	
	@GetMapping(path = "/fetchroles")
	public List<RoleMaster> getUserRoles(){
		logger.info("Fetching the User Roles");
		return configDataService.getUserRoles();
	}
	
	@GetMapping(path = "/fetchLookups")
	public List<LookupParams> fetchLookups(){
		logger.info("Fetching the fetchLookups");
		return configDataService.fetchLookups();
	}
	
	@GetMapping(path = "/fetchEntityTypes")
	public List<LookupParams> fetchEntityTypes(){
		logger.info("Fetching the fetchLookups for entity types");
		return configDataService.fetchLookupForParam(HaritContstants.ENTITY_TYPE);
	}
	
	@GetMapping(path = "/fetchAIModels")
	public List<LookupParams> fetchAIModels(){
		logger.info("Fetching the fetchLookups for AI models");
		return configDataService.fetchLookupForParam(HaritContstants.HARIT_AI_MODEL);
	}
}
