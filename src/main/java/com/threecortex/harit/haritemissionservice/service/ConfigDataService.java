/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.model.LookupParams;
import com.threecortex.harit.haritemissionservice.model.RoleMaster;

/**
 * 
 */
@Service
public interface ConfigDataService {

	List<RoleMaster> getUserRoles();

	List<LookupParams> fetchLookups();

	List<LookupParams> fetchLookupForParam(String paramName);

}
