/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.common.HaritContstants;
import com.threecortex.harit.haritemissionservice.common.HaritQueryConfig;
import com.threecortex.harit.haritemissionservice.common.QueryFactory;
import com.threecortex.harit.haritemissionservice.dto.EmailObject;
import com.threecortex.harit.haritemissionservice.dto.HaritServiceReponse;
import com.threecortex.harit.haritemissionservice.dto.LoggedInUserDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskSubParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskTemplateDTO;
import com.threecortex.harit.haritemissionservice.dto.SignupRequest;
import com.threecortex.harit.haritemissionservice.model.EntityMaster;
import com.threecortex.harit.haritemissionservice.model.MapEntityWithTemplate;
import com.threecortex.harit.haritemissionservice.model.RiskParamMaster;
import com.threecortex.harit.haritemissionservice.model.RiskTemplateComposition;
import com.threecortex.harit.haritemissionservice.model.RiskTemplateMaster;
import com.threecortex.harit.haritemissionservice.model.SubRiskParamMaster;
import com.threecortex.harit.haritemissionservice.repository.EntityMasterRepo;
import com.threecortex.harit.haritemissionservice.repository.MapEntityWithTemplateRepo;
import com.threecortex.harit.haritemissionservice.repository.RiskParamMasterRepo;
import com.threecortex.harit.haritemissionservice.repository.RiskTemplateCompositionRepo;
import com.threecortex.harit.haritemissionservice.repository.RiskTemplateMasterRepo;
import com.threecortex.harit.haritemissionservice.repository.SubRiskParamMasterRepo;
import com.threecortex.harit.haritemissionservice.service.EmailSenderService;
import com.threecortex.harit.haritemissionservice.service.RiskConfigService;
import com.threecortex.harit.haritemissionservice.service.S4SIntegratorService;

import jakarta.transaction.Transactional;

/**
 * 
 */
@Service
public class RiskConfigServiceImpl implements RiskConfigService{
	
	private static final Logger logger = LoggerFactory.getLogger(RiskConfigServiceImpl.class);

	@Autowired
	private RiskParamMasterRepo riskParamRepo;
	
	@Autowired
	private EntityMasterRepo entityMasterRepo;
	
	@Autowired
	private RiskTemplateMasterRepo riskTemplateMasterRepo;

	@Autowired
	private S4SIntegratorService s4sIntegrtorService;
	
	@Autowired
	private RiskTemplateCompositionRepo riskTemplateCompositionRepo;
	
	@Autowired
	private MapEntityWithTemplateRepo mapEntityWithTemplateRepo;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private SubRiskParamMasterRepo subRiskParamRepo;
	
	@Autowired
	private QueryFactory queryFactory;
	
	@Override
	public List<RiskParamMaster> getRiskParams() {
		return riskParamRepo.findByOrderByRiskCategoryId();
	}

	@Override
	public List<SubRiskParamMaster> getRiskSubParams(Long riskCategoryId) {
		return subRiskParamRepo.findByRiskParamMasterRiskCategoryIdOrderBySubCategoryId(riskCategoryId);
	}

	@Override
	public HaritServiceReponse saveRiskParam(RiskParamDTO riskParamDTO) {
		String responseMessage = new String();
		RiskParamMaster riskParamMaster = riskParamDTO.getRiskParamMaster();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			Date date = new Date();
			riskParamMaster.setOcStatus(HaritContstants.OPENSTATUS);
			riskParamMaster.setDeleted(HaritContstants.BOOLEAN_FALSE_STATUS);
			if(riskParamMaster.getRiskCategoryId() == null) {
				//creation of new project
				riskParamMaster.setCreatedBy(riskParamDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				riskParamMaster.setCreatedDate(new Timestamp(date.getTime()));
				riskParamMaster.setVerifiedBy(riskParamDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				riskParamMaster.setVerifiedDate(new Timestamp(date.getTime()));

			}else {
				//this is modification of the project
				riskParamMaster.setUpdatedBy(riskParamDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				riskParamMaster.setUpdatedDate(new Timestamp(date.getTime()));
				responseMessage = String.format(HaritContstants.RISKPARAM_MODIFIED_SUCCESS, riskParamMaster.getRiskCategoryId());

			}
			riskParamMaster = riskParamRepo.save(riskParamMaster);	
			responseMessage = String.format(HaritContstants.RISKPARAM_CREATED, riskParamMaster.getRiskCategoryId());
		}catch (DataIntegrityViolationException de) {
			logger.error("Exception occured while saving the project due to data integrity issues::{}", de.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.RISKPARAM_NAME_DUPLICATE;
		}		
		catch(Exception e) {
			logger.error("Exception occured while saving the project ::{}", e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.RISKPARAM_SAVE_FAILED;
		}

		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	@Override
	public HaritServiceReponse closeRiskParam(Long riskCategoryId, LoggedInUserDTO loggedInUserDTO) {
		String responseMessage = new String();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			queryFactory.setCloseStatus(loggedInUserDTO, HaritQueryConfig.CLOSE_RISKPARAM_STATUS, riskCategoryId, HaritContstants.CLOSE_STATUS);
			responseMessage = String.format(HaritContstants.DELETE_ACTION_SUCCESS, riskCategoryId);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured "+  e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.FAILED_PROCESS_REQUEST;
		}
		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	@Override
	public HaritServiceReponse saveRiskSubParam(RiskSubParamDTO riskSubParamDTO) {
		String responseMessage = new String();
		SubRiskParamMaster subRiskParam = riskSubParamDTO.getSubRiskParamMaster();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			Date date = new Date();
			subRiskParam.setOcStatus(HaritContstants.OPENSTATUS);
			subRiskParam.setDeleted(HaritContstants.BOOLEAN_FALSE_STATUS);
			if(subRiskParam.getSubCategoryId() == null) {
				subRiskParam.setCreatedBy(riskSubParamDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				subRiskParam.setCreatedDate(new Timestamp(date.getTime()));
				subRiskParam.setVerifiedBy(riskSubParamDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				subRiskParam.setVerifiedDate(new Timestamp(date.getTime()));
				subRiskParam = subRiskParamRepo.save(subRiskParam);	
				responseMessage = String.format(HaritContstants.RISKPARAM_CREATED, subRiskParam.getSubCategoryId());
			}else {
				//this is modification of the project
				subRiskParam.setUpdatedBy(riskSubParamDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				subRiskParam.setUpdatedDate(new Timestamp(date.getTime()));
				subRiskParam = subRiskParamRepo.save(subRiskParam);
				responseMessage = String.format(HaritContstants.RISKPARAM_MODIFIED_SUCCESS, subRiskParam.getSubCategoryId());
			}

		}catch (DataIntegrityViolationException de) {
			logger.error("Exception occured while saving the project scope due to data integrity issues::{}", de.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.RISKPARAM_NAME_DUPLICATE;
		}catch(Exception e) {
			logger.error("Exception occured while saving the project ::{}", e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.RISKPARAM_SAVE_FAILED;
		}

		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	@Override
	public HaritServiceReponse closeRiskSubParam(Long subRiskCategoryID, LoggedInUserDTO loggedInUserDTO) {
		String responseMessage = new String();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			queryFactory.setCloseStatus(loggedInUserDTO, HaritQueryConfig.CLOSE_SUBRISKPARAM_STATUS, subRiskCategoryID, HaritContstants.CLOSE_STATUS);
			responseMessage = String.format(HaritContstants.DELETE_ACTION_SUCCESS, subRiskCategoryID);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured "+  e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.FAILED_PROCESS_REQUEST;
		}
		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	@Override
	@Transactional
	public HaritServiceReponse saveRiskTemplate(RiskTemplateDTO riskTemplateDTO) throws Exception {
		String responseMessage = new String();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			riskTemplateDTO.setRiskTemplateMaster(saveRiskTemplateMaster(riskTemplateDTO.getRiskTemplateMaster(), riskTemplateDTO.getLoggedInUserDTO()));
			logger.debug("Created by::{}",riskTemplateDTO.getRiskTemplateMaster().getCreatedBy());
			responseMessage = String.format(HaritContstants.RISKTEMPLATE_CREATED, riskTemplateDTO.getRiskTemplateMaster().getTemplateId());
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception occured while saving the risk template master ::{}", e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.RISKTEMPLATE_SAVE_FAILED;
			throw new Exception(responseMessage);
		}
		logger.info("Risk Template Master ID after Saving :::{}",riskTemplateDTO.getRiskTemplateMaster().getTemplateId());
		if(apiResponse.getResponseStatus().equals(HaritContstants.API_RESPONSE_SUCCESS)) {
			//processing for the compositions
			for(RiskTemplateComposition riskTemplateComposition : riskTemplateDTO.getRiskTemplateCompositionList()) {
				//processng for each composition
				try {
					logger.debug("Saving the riskTemplate Composition for riskCategory ID:{} and riskSubCategory ID::{}", riskTemplateComposition.getRiskCategoryId(), riskTemplateComposition.getSubCategoryId());
					//setting the parent risk tempate master which is saved latest above
					riskTemplateComposition.setRiskTemplateMaster(riskTemplateDTO.getRiskTemplateMaster());
					logger.debug("risk template master createdBy::{}",riskTemplateDTO.getRiskTemplateMaster().getCreatedBy());
					apiResponse = saveRiskTemplateComp(riskTemplateComposition, riskTemplateDTO.getLoggedInUserDTO());
					logger.debug("Post risk template comp save response status:::{}", apiResponse.getResponseStatus());
					logger.debug("Post risk template comp save response message:::{}", apiResponse.getResponseMessage());
				}catch(Exception e) {
					e.printStackTrace();
					logger.error("Exception occured while saving the risk template composition ::{}", e.getMessage());
					apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
					responseMessage = HaritContstants.RISKTEMPLATECOMP_SAVE_FAILED;
					throw new Exception(responseMessage);
				}
			}
		}
		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	private HaritServiceReponse saveRiskTemplateComp(RiskTemplateComposition riskTemplateComposition,
			LoggedInUserDTO loggedInUserDTO) throws Exception {
		String responseMessage = new String();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			Date date = new Date();
			riskTemplateComposition.setOcStatus(HaritContstants.OPENSTATUS);
			riskTemplateComposition.setDeleted(HaritContstants.BOOLEAN_FALSE_STATUS);
			if(riskTemplateComposition.getCompositionId() == null) {
				//creation of new composition
				riskTemplateComposition.setCreatedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				riskTemplateComposition.setCreatedDate(new Timestamp(date.getTime()));
				riskTemplateComposition.setVerifiedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				riskTemplateComposition.setVerifiedDate(new Timestamp(date.getTime()));

			}else {
				//this is modification of the project
				riskTemplateComposition.setUpdatedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				riskTemplateComposition.setUpdatedDate(new Timestamp(date.getTime()));
				//responseMessage = String.format(HaritContstants.RISKTEMPLATECOMP_MODIFIED_SUCCESS, riskTemplateComposition.getCompositionId());

			}
			logger.debug("Saving the composition and before save the comp ID::{}", riskTemplateComposition.getCompositionId());
			riskTemplateComposition = riskTemplateCompositionRepo.save(riskTemplateComposition);	
			//responseMessage = String.format(HaritContstants.RISKTEMPLATECOMP_CREATED, riskTemplateComposition.getCompositionId());
		}catch (DataIntegrityViolationException de) {
			de.printStackTrace();
			logger.error("Exception occured while saving the risk template composition due to data integrity issues::{}", de.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.RISKTEMPLATECOMP_NAME_DUPLICATE;
			//throw new Exception(responseMessage);
		}		
		catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception occured while saving the risk template ::{}", e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.RISKTEMPLATECOMP_SAVE_FAILED;
			//throw new Exception(responseMessage);
		}
		logger.debug("Setting the response message => {}", responseMessage);
		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	private RiskTemplateMaster saveRiskTemplateMaster(RiskTemplateMaster riskTemplateMaster,
			LoggedInUserDTO loggedInUserDTO) {
		try {
			Date date = new Date();
			riskTemplateMaster.setOcStatus(HaritContstants.OPENSTATUS);
			riskTemplateMaster.setDeleted(HaritContstants.BOOLEAN_FALSE_STATUS);
			if(riskTemplateMaster.getTemplateId() == null) {
				//creation of new project
				riskTemplateMaster.setCreatedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				riskTemplateMaster.setCreatedDate(new Timestamp(date.getTime()));
				riskTemplateMaster.setVerifiedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				riskTemplateMaster.setVerifiedDate(new Timestamp(date.getTime()));

			}else {
				//this is modification of the project
				riskTemplateMaster.setUpdatedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				riskTemplateMaster.setUpdatedDate(new Timestamp(date.getTime()));

			}
			riskTemplateMaster = riskTemplateMasterRepo.save(riskTemplateMaster);	
		}catch (DataIntegrityViolationException de) {
			logger.error("Exception occured while saving the risk template due to data integrity issues::{}", de.getMessage());
			//apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			//responseMessage = HaritContstants.RISKTEMPLATE_NAME_DUPLICATE;
		}		
		catch(Exception e) {
			logger.error("Exception occured while saving the risk template ::{}", e.getMessage());
			//apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			//responseMessage = HaritContstants.RISKTEMPLATE_SAVE_FAILED;
		}
		return riskTemplateMaster;
	}

	@Override
	public List<RiskTemplateDTO> fetchRiskTemplates() {
		List<RiskTemplateMaster> riskTemplateMasterList = riskTemplateMasterRepo.findByOrderByTemplateId();
		List<RiskTemplateDTO> riskTemplateDTOList = new ArrayList<RiskTemplateDTO>();
		LoggedInUserDTO loggedInUserDTO = new LoggedInUserDTO();
		for(RiskTemplateMaster riskTemplateMaster : riskTemplateMasterList) {
			RiskTemplateDTO riskTemplateDTO = new RiskTemplateDTO();
			List<RiskTemplateComposition> riskTemplateComps = riskTemplateCompositionRepo.findByRiskTemplateMasterOrderByCompositionId(riskTemplateMaster);
			riskTemplateDTO.setRiskTemplateMaster(riskTemplateMaster);
			riskTemplateDTO.setRiskTemplateCompositionList(riskTemplateComps);
			riskTemplateDTO.setLoggedInUserDTO(loggedInUserDTO);
			riskTemplateDTOList.add(riskTemplateDTO);
		}
		return riskTemplateDTOList;
	}

	@Override
	public List<RiskTemplateComposition> fetchRiskTemplateComps(Long riskTemplateId) {
		return riskTemplateCompositionRepo.findByRiskTemplateMasterTemplateIdOrderByCompositionId(riskTemplateId);
	}

	@Override
	public HaritServiceReponse closeRiskTemplate(Long riskTemplateId, LoggedInUserDTO loggedInUserDTO) {
		String responseMessage = new String();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			queryFactory.setCloseStatus(loggedInUserDTO, HaritQueryConfig.CLOSE_RISKTEMPLATE_STATUS, riskTemplateId, HaritContstants.CLOSE_STATUS);
			responseMessage = String.format(HaritContstants.DELETE_ACTION_SUCCESS, riskTemplateId);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured "+  e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.FAILED_PROCESS_REQUEST;
		}
		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	@Override
	public HaritServiceReponse mapRiskTemplate(Long riskTemplateId, Long entityId, LoggedInUserDTO loggedInUserDTO) {
		String responseMessage = new String();
		MapEntityWithTemplate mapEntityWithTemplate = new MapEntityWithTemplate();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			Date date = new Date();
			mapEntityWithTemplate.setOcStatus(HaritContstants.OPENSTATUS);
			mapEntityWithTemplate.setDeleted(HaritContstants.BOOLEAN_FALSE_STATUS);
			mapEntityWithTemplate.setTemplateId(riskTemplateId);
			mapEntityWithTemplate.setEntityId(entityId);
			EntityMaster entityData = entityMasterRepo.findByEntityId(entityId);
			if(mapEntityWithTemplate.getMapId() == null) {
				mapEntityWithTemplate.setCreatedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				mapEntityWithTemplate.setCreatedDate(new Timestamp(date.getTime()));
				mapEntityWithTemplate.setVerifiedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				mapEntityWithTemplate.setVerifiedDate(new Timestamp(date.getTime()));
				SignupRequest createUserRequest = new SignupRequest(entityData.getLoginName(), entityData.getEmailAddr(), entityData.getRoleMaster().getRoleName(), "SayaUser"+ Math.random());
				apiResponse = s4sIntegrtorService.createUserInS4S(createUserRequest);
				logger.info("Returned API response from S4s service:::{}",apiResponse.getResponseStatus());
				if(apiResponse.getResponseStatus().equals(HaritContstants.API_RESPONSE_FAILURE)) {
					return apiResponse;
				}
				mapEntityWithTemplate = mapEntityWithTemplateRepo.save(mapEntityWithTemplate);
				responseMessage = String.format(HaritContstants.ENTITY_LOGIN_CREATED, entityData.getLoginName());
				EmailObject emailObject = new EmailObject(entityData.getEmailAddr(), HaritContstants.SUB_ONBOARD_SUCCESS, String.format(HaritContstants.BODY_ONBOARDSUCCESS,entityData.getFirstName()));
				CompletableFuture<String> emailSendResponse;
				emailSendResponse = emailSenderService.sendEmail(emailObject);
				logger.info("emailSendResponse::{}", emailSendResponse);
			}else {
				//this is modification 
				mapEntityWithTemplate.setUpdatedBy(loggedInUserDTO.getLoggedInUserId().intValue());
				mapEntityWithTemplate.setUpdatedDate(new Timestamp(date.getTime()));
				mapEntityWithTemplate = mapEntityWithTemplateRepo.save(mapEntityWithTemplate);
				responseMessage = String.format(HaritContstants.ENTITY_MODIFIED_SUCCESS, entityData.getLoginName());
			}
			responseMessage = responseMessage + String.format(HaritContstants.ENTITY_ONBOARD_SUCCESS, entityData.getEntityId());
		}catch(Exception e) {
			logger.error("Exception occured while saving the user ::{}", e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			apiResponse.setResponseMessage(HaritContstants.ENTITY_ONBOARD_FAILED);
		}
		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	@Override
	public MapEntityWithTemplate fetchMappedRiskTemplate(Long entityId) {	
		return mapEntityWithTemplateRepo.findByEntityId(entityId);
	}
}
