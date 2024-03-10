/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service;

import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.dto.HaritServiceReponse;
import com.threecortex.harit.haritemissionservice.dto.SignupRequest;
import com.threecortex.harit.haritemissionservice.dto.ValidateEntityRequest;


/**
 * 
 */
@Service
public interface S4SIntegratorService {
	public HaritServiceReponse createUserInS4S(SignupRequest createEnityRequest);

	public HaritServiceReponse deActivateUserInS4S(ValidateEntityRequest enityRequest);

}
