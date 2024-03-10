/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.model.LookupParams;
import com.threecortex.harit.haritemissionservice.model.RoleMaster;
import com.threecortex.harit.haritemissionservice.repository.LookupParamsRepo;
import com.threecortex.harit.haritemissionservice.repository.RoleMasterRepo;
import com.threecortex.harit.haritemissionservice.service.ConfigDataService;


/**
 * 
 */
@Service
public class ConfigDataServiceImpl implements ConfigDataService{

	@Autowired
	private RoleMasterRepo roleMasterRepo;
	
	@Autowired
	private LookupParamsRepo lookupParamsRepo;

	@Override
	public List<RoleMaster> getUserRoles() {
		return roleMasterRepo.findByOrderByRoleIdAsc();
	}

	@Override
	public List<LookupParams> fetchLookups() {
		return lookupParamsRepo.findByOrderByLookupIdAsc();
	}

	@Override
	public List<LookupParams> fetchLookupForParam(String paramName) {
		return lookupParamsRepo.findByLookupParamOrderByLookupIdAsc(paramName);
	}

}
