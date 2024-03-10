/**
 * 
 */
package com.threecortex.harit.haritemissionservice.common;

/**
 * 
 */
public class HaritQueryConfig {

	public static final String CLOSE_ENTITY_STATUS = "update tb_mst_entity set oc_status = :ocStatus, updated_by = :updatedBy, updated_date = :updatedDate where entity_id = :Id";
	public static final String CLOSE_RISKPARAM_STATUS = "update tb_mst_risk_param set oc_status = :ocStatus, updated_by = :updatedBy, updated_date = :updatedDate where risk_category_id = :Id";
	public static final String CLOSE_SUBRISKPARAM_STATUS = "update tb_mst_risk_sub_param set oc_status = :ocStatus, updated_by = :updatedBy, updated_date = :updatedDate where sub_category_id = :Id";
	public static final String CLOSE_RISKTEMPLATE_STATUS = "update TB_MST_RISK_TEMPLATE set oc_status = :ocStatus, updated_by = :updatedBy, updated_date = :updatedDate where template_id = :Id";;
	public static final String FETCH_UNMAPPED_ENTITIES = "select * from tb_mst_entity tme where entity_id not in (select tmewt.entity_id from tb_map_entity_with_template tmewt, tb_mst_risk_template tmrt where tmewt.template_id = tmrt.template_id and tmrt.oc_status = 'O') and tme.oc_status = 'O' order by tme.entity_id desc";
}
