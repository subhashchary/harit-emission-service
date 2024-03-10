/**
 * 
 */
package com.threecortex.harit.haritemissionservice.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.dto.LoggedInUserDTO;
import com.threecortex.harit.haritemissionservice.model.EntityMaster;


/**
 * 
 */
@Service
public class QueryFactory {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(QueryFactory.class);
	
	public int setCloseStatus(LoggedInUserDTO loggedInUserDetails, String queryString, Long Id, String status) {
		logger.info("queryString {}",queryString);

		Date date = new Date();
		
		Map<String, Object> paramSource = new HashMap<>();
		paramSource.put("Id", Id);
		paramSource.put("updatedDate", new Timestamp(date.getTime()));
		paramSource.put("updatedBy", loggedInUserDetails.getLoggedInUserId());
		paramSource.put("ocStatus", status);
		logger.info("paramSource:::{}",paramSource);
		int updCount= jdbcTemplate.update(queryString, paramSource);
		logger.info("updCount  {}",updCount);
		return updCount;		
	}

	public List<EntityMaster> fetchUnMappedEntities() {
		Map<String, Object> paramSource = new HashMap<>();
		logger.info("Fetching unmapped entities query is {} for params {}", HaritQueryConfig.FETCH_UNMAPPED_ENTITIES, paramSource);
		return jdbcTemplate.query(HaritQueryConfig.FETCH_UNMAPPED_ENTITIES, paramSource, new BeanPropertyRowMapper<>(EntityMaster.class));
	}

}
