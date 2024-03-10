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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.threecortex.harit.haritemissionservice.common.HaritContstants;
import com.threecortex.harit.haritemissionservice.dto.HaritServiceReponse;
import com.threecortex.harit.haritemissionservice.dto.LoggedInUserDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskSubParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskTemplateDTO;
import com.threecortex.harit.haritemissionservice.model.EntityMaster;
import com.threecortex.harit.haritemissionservice.model.MapEntityWithTemplate;
import com.threecortex.harit.haritemissionservice.model.RiskParamMaster;
import com.threecortex.harit.haritemissionservice.model.RiskTemplateComposition;
import com.threecortex.harit.haritemissionservice.model.SubRiskParamMaster;
import com.threecortex.harit.haritemissionservice.service.EntityService;
import com.threecortex.harit.haritemissionservice.service.RiskConfigService;

/**
 * 
 */
@RestController
@RequestMapping(path = "/emissionConfig", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskEmissionController {

	private static final Logger logger = LoggerFactory.getLogger(RiskEmissionController.class);

	@Autowired
	private RiskConfigService riskConfigService;
	
	@Autowired
	private EntityService entityService;	

	@GetMapping(path = "/fetchRiskParams")
	public List<RiskParamMaster> getRiskParams(){
		logger.info("Fetching the Risk Params");
		return riskConfigService.getRiskParams();
	}

	@GetMapping(path = "/fetchRiskSubParams")
	public List<SubRiskParamMaster> getRiskSubParams(@RequestParam Long riskCategoryId){
		logger.info("Fetching the Risk Sub Params for risk category id:::{}", riskCategoryId);
		return riskConfigService.getRiskSubParams(riskCategoryId);
	}

	@PostMapping(path = "/saveRiskParam")
	public HaritServiceReponse saveRiskParam(@RequestBody RiskParamDTO riskParamDTO) {
		logger.info("Save Risk Param Called {}");
		return riskConfigService.saveRiskParam(riskParamDTO);
	}

	@PostMapping(path = "/closeRiskParam")
	public HaritServiceReponse closeRiskParam(@RequestParam Long riskCategoryId, @RequestBody LoggedInUserDTO loggedInUserDTO) {
		logger.info("Close Risk Param Called for risk category ID {}", riskCategoryId);
		return riskConfigService.closeRiskParam(riskCategoryId, loggedInUserDTO);
	}

	@PostMapping(path = "/saveRiskSubParam")
	public HaritServiceReponse saveRiskSubParam(@RequestBody RiskSubParamDTO riskSubParamDTO) {
		logger.info("Save Risk Sub param Called {}");
		return riskConfigService.saveRiskSubParam(riskSubParamDTO);
	}

	@PostMapping(path = "/closeRiskSubParam")
	public HaritServiceReponse closeRiskSubParam(@RequestParam Long subRiskCategoryID, @RequestBody LoggedInUserDTO loggedInUserDTO) {
		logger.info("Close risk sub param Called for sub category ID {}", subRiskCategoryID);
		return riskConfigService.closeRiskSubParam(subRiskCategoryID, loggedInUserDTO);
	}

	@PostMapping(path = "/saveRiskTemplate")
	public HaritServiceReponse saveRiskTemplate(@RequestBody RiskTemplateDTO riskTemplateDTO) {
		logger.info("Save Risk template Called {}");
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		String responseMessage = new String();		
		try {
			apiResponse = riskConfigService.saveRiskTemplate(riskTemplateDTO);
			responseMessage = apiResponse.getResponseMessage();
		}catch (Exception e) {
			e.printStackTrace();
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.RISKTEMPLATECOMP_SAVE_FAILED;
		}
		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	@GetMapping(path = "/fetchRiskTemplates")
	public List<RiskTemplateDTO> fetchRiskTemplates(){
		logger.info("Fetching the Risk templates");
		return riskConfigService.fetchRiskTemplates();
	}

	@GetMapping(path = "/fetchRiskTemplateComps")
	public List<RiskTemplateComposition> fetchRiskTemplateComps(@RequestParam Long riskTemplateId){
		logger.info("Fetching the Risk template compositions for risk template ID::{}", riskTemplateId);
		return riskConfigService.fetchRiskTemplateComps(riskTemplateId);
	}

	@PostMapping(path = "/closeRiskTemplate")
	public HaritServiceReponse closeRiskTemplate(@RequestParam Long riskTemplateId, @RequestBody LoggedInUserDTO loggedInUserDTO) {
		logger.info("Close risk template Called for risk template ID {}", riskTemplateId);
		return riskConfigService.closeRiskTemplate(riskTemplateId, loggedInUserDTO);
	}

	@PostMapping(path = "/mapRiskTemplate")
	public HaritServiceReponse mapRiskTemplate(@RequestParam Long riskTemplateId, @RequestParam Long entityId, @RequestBody LoggedInUserDTO loggedInUserDTO) {
		logger.info("map risk template Called for risk template ID {} and enity ID::{}", riskTemplateId, entityId);
		return riskConfigService.mapRiskTemplate(riskTemplateId, entityId, loggedInUserDTO);
	}
	
	@GetMapping(path = "/fetchMappedRiskTemplate")
	public MapEntityWithTemplate fetchMappedRiskTemplate(@RequestParam Long entityId){
		logger.info("Fetching the mapped Risk template for risk entity ID::{}", entityId);
		return riskConfigService.fetchMappedRiskTemplate(entityId);
	}
	
	@GetMapping(path = "/fetchUnMappedEntities")
	public List<EntityMaster> fetchUnMappedEntities(){
		logger.info("Fetching the unmapped entities ");
		return entityService.fetchUnMappedEntities();
	}
	
	
	@PostMapping(path = "/closeEntity")
	public HaritServiceReponse closeEntity(@RequestParam Long entityId, @RequestBody LoggedInUserDTO loggedInUserDetailsDTO) {
		logger.info("Closing the Entity  Called for entity id {}", entityId);
		return entityService.closeUser(entityId, loggedInUserDetailsDTO);
	}
	
	@GetMapping(path = "/fetchEntities")
	public List<EntityMaster> fetchEntities(){
		logger.info("Fetching the entities ");
		return entityService.fetchEntities();
	}	
}
