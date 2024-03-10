/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.common.HaritContstants;
import com.threecortex.harit.haritemissionservice.common.HaritQueryConfig;
import com.threecortex.harit.haritemissionservice.common.QueryFactory;
import com.threecortex.harit.haritemissionservice.dto.EmailObject;
import com.threecortex.harit.haritemissionservice.dto.EntityDTO;
import com.threecortex.harit.haritemissionservice.dto.HaritServiceReponse;
import com.threecortex.harit.haritemissionservice.dto.LoggedInUserDTO;

import com.threecortex.harit.haritemissionservice.dto.ValidateEntityRequest;
import com.threecortex.harit.haritemissionservice.model.EntityMaster;
import com.threecortex.harit.haritemissionservice.repository.EntityMasterRepo;
import com.threecortex.harit.haritemissionservice.service.EmailSenderService;
import com.threecortex.harit.haritemissionservice.service.EntityService;
import com.threecortex.harit.haritemissionservice.service.S4SIntegratorService;

/**
 * 
 */
@Service
public class EntityServiceImpl implements EntityService{
	
	private static final Logger logger = LoggerFactory.getLogger(EntityServiceImpl.class);
	
	@Autowired
	private EntityMasterRepo entityMasterRepo;
	
	@Autowired
	private QueryFactory queryFactory;

	@Autowired
	private S4SIntegratorService s4sIntegrtorService;
	
	@Autowired
	private EmailSenderService emailSenderService;

	@Override
	public HaritServiceReponse createEnity(EntityDTO entityDTO) {
		String responseMessage = new String();
		EntityMaster entityData = entityDTO.getEntityMaster();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			Date date = new Date();
			entityData.setOcStatus(HaritContstants.OPENSTATUS);
			entityData.setDeleted(HaritContstants.BOOLEAN_FALSE_STATUS);
			entityData.setLoginName(entityDTO.getEntityMaster().getLoginName().toUpperCase());//converting everything to uppercase
			if(entityData.getEntityId() == null) {
				//creation of new user 
				entityData.setCreatedBy(entityDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				entityData.setCreatedDate(new Timestamp(date.getTime()));
				entityData.setVerifiedBy(entityDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				entityData.setVerifiedDate(new Timestamp(date.getTime()));
				//SignupRequest createUserRequest = new SignupRequest(entityData.getLoginName(), entityData.getEmailAddr(), entityData.getRoleMaster().getRoleName(), "SayaUser"+ Math.random());
				//apiResponse = s4sIntegrtorService.createUserInS4S(createUserRequest);
				//logger.info("Returned API response from S4s service:::{}",apiResponse.getResponseStatus());
				//if(apiResponse.getResponseStatus().equals(HaritContstants.API_RESPONSE_FAILURE)) {
				//	return apiResponse;
				//}
				entityData = entityMasterRepo.save(entityData);
				responseMessage = String.format(HaritContstants.ENTITY_LOGIN_CREATED, entityData.getLoginName());
				CompletableFuture<String> emailSendResponse;
				EmailObject emailObject = new EmailObject(entityData.getEmailAddr(), HaritContstants.SUB_ONBOARDREQUEST_SUCCESS, String.format(HaritContstants.BODY_ONBOARDREQUEST,entityData.getFirstName()));
				emailSendResponse = emailSenderService.sendEmail(emailObject);
				logger.info("calling method::{}", emailSendResponse);
			}else {
				//this is modification of the user
				entityData.setUpdatedBy(entityDTO.getLoggedInUserDTO().getLoggedInUserId().intValue());
				entityData.setUpdatedDate(new Timestamp(date.getTime()));
				entityData = entityMasterRepo.save(entityData);
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
	public EntityMaster getEntityById(Long entityId) {
		return entityMasterRepo.findByEntityId(entityId);
	}

	@Override
	public EntityMaster getByLoginName(String loginName) {
		return entityMasterRepo.findByLoginName(loginName);
	}

	@Override
	public HaritServiceReponse closeUser(Long entityId, LoggedInUserDTO loggedInUserDetailsDTO) {
		String responseMessage = new String();
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS);
		try {
			EntityMaster entityMaster = getEntityById(entityId);
			ValidateEntityRequest entityRequest = new ValidateEntityRequest(entityMaster.getLoginName());
			apiResponse = s4sIntegrtorService.deActivateUserInS4S(entityRequest);

			if(apiResponse.getResponseStatus().equals(HaritContstants.API_RESPONSE_FAILURE)) {
				return apiResponse;
			}
			EntityMaster entityData = entityMasterRepo.findByEntityId(entityId);

			queryFactory.setCloseStatus(loggedInUserDetailsDTO, HaritQueryConfig.CLOSE_ENTITY_STATUS, entityId, HaritContstants.CLOSE_STATUS);
			responseMessage = String.format(HaritContstants.DELETE_ACTION_SUCCESS, entityId);
			EmailObject emailObject = new EmailObject(entityData.getEmailAddr(), HaritContstants.SUB_CLOSE_ENTITY, String.format(HaritContstants.BODY_CLOSE_ENTITY,entityData.getFirstName()));
			emailSenderService.sendEmail(emailObject);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured "+  e.getMessage());
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			responseMessage = HaritContstants.FAILED_PROCESS_REQUEST;
		}
		apiResponse.setResponseMessage(responseMessage);
		return apiResponse;
	}

	@Override
	public List<EntityMaster> fetchEntities() {
		return entityMasterRepo.findByOrderByEntityIdDesc();
	}

	@Override
	public List<EntityMaster> fetchUnMappedEntities() {

		return queryFactory.fetchUnMappedEntities();
	}

}
