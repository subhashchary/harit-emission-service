/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.dto.HaritServiceReponse;
import com.threecortex.harit.haritemissionservice.dto.LoggedInUserDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskSubParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskTemplateDTO;
import com.threecortex.harit.haritemissionservice.model.MapEntityWithTemplate;
import com.threecortex.harit.haritemissionservice.model.RiskParamMaster;
import com.threecortex.harit.haritemissionservice.model.RiskTemplateComposition;
import com.threecortex.harit.haritemissionservice.model.SubRiskParamMaster;

/**
 * 
 */
@Service
public interface RiskConfigService {

	List<RiskParamMaster> getRiskParams();

	List<SubRiskParamMaster> getRiskSubParams(Long riskCategoryId);

	HaritServiceReponse saveRiskParam(RiskParamDTO riskParamDTO);

	HaritServiceReponse closeRiskParam(Long riskCategoryId, LoggedInUserDTO loggedInUserDTO);

	HaritServiceReponse saveRiskSubParam(RiskSubParamDTO riskSubParamDTO);

	HaritServiceReponse closeRiskSubParam(Long subRiskCategoryID, LoggedInUserDTO loggedInUserDTO);

	HaritServiceReponse saveRiskTemplate(RiskTemplateDTO riskTemplateDTO) throws Exception;

	List<RiskTemplateDTO> fetchRiskTemplates();

	List<RiskTemplateComposition> fetchRiskTemplateComps(Long riskTemplateId);

	HaritServiceReponse closeRiskTemplate(Long riskTemplateId, LoggedInUserDTO loggedInUserDTO);

	HaritServiceReponse mapRiskTemplate(Long riskTemplateId, Long entityId, LoggedInUserDTO loggedInUserDTO);

	MapEntityWithTemplate fetchMappedRiskTemplate(Long riskTemplateId);

}
