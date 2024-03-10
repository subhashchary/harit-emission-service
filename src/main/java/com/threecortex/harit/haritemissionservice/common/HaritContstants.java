/**
 * 
 */
package com.threecortex.harit.haritemissionservice.common;

/**
 * 
 */
public class HaritContstants {
	
	/**
	 * Prevent unnecessary instantiation of objects
	 */
	private HaritContstants() {
		
	}
	
	public final static String API_RESPONSE_SUCCESS = "SUCCESS";
	public final static String API_RESPONSE_FAILURE = "FAILURE";
	public static final String OPENSTATUS = "O";
	public final static String CLOSE_STATUS = "C";
	public final static boolean BOOLEAN_TRUE_STATUS = true;
	public final static boolean BOOLEAN_FALSE_STATUS = false;
	public final static String ENTITY_LOGIN_CREATED = "Successfully created enity with Login Name: %s, onboarded ";
	public final static String ENTITY_MODIFIED_SUCCESS = "Successfully modified enity master with Login Name: %s,";
	public final static String ENTITY_ONBOARD_SUCCESS = " with user registration ID %s";
	public final static String ENTITY_ONBOARD_FAILED = "Enity onboarding failed, contact support!";

	public final static String FAILED_S4S_SERVICE = "Failed processing request for user activation/deactivation, as s4s service failed!!!";
	public final static String DELETE_ACTION_SUCCESS = "Successfully closed record with id: %s";
	public final static String FAILED_PROCESS_REQUEST = "Failed processing request !!!";
	public static final String RISKPARAM_MODIFIED_SUCCESS = "Successfully modified risk param with risk category ID: %s";
	public static final String RISKPARAM_CREATED = "Successfully modified risk param with risk category ID: %s";
	public static final String RISKPARAM_NAME_DUPLICATE = "Risk Param Name is already present please  different name and try!!!";
	public static final String RISKPARAM_SAVE_FAILED = "Risk Param Save failed, contact support!";
	public static final String ENTITY_TYPE = "ENTITY_TYPE";
	public static final String HARIT_AI_MODEL = "HARIT_AI_MODEL";
	public static final String RISKTEMPLATE_MODIFIED_SUCCESS = "Successfully modified risk template with template ID: %s,";
	public static final String RISKTEMPLATE_CREATED = "Successfully created risk template with template ID: %s";
	public static final String RISKTEMPLATE_NAME_DUPLICATE = "Risk template Name is already present please enter different name and try !!!";
	public static final String RISKTEMPLATE_SAVE_FAILED = "Risk template Save failed, contact support!";
	public static final String RISKTEMPLATECOMP_MODIFIED_SUCCESS = "Successfully modified risk template composition with composition ID: %s,";
	public static final String RISKTEMPLATECOMP_CREATED = "Successfully created risk template composition with composition ID: %s";
	public static final String RISKTEMPLATECOMP_NAME_DUPLICATE = "Risk template composition Name is already present please enter different name and try !!!";
	public static final String RISKTEMPLATECOMP_SAVE_FAILED = "Risk template composition Save failed, contact support!";
	public static final String SUB_ONBOARDREQUEST_SUCCESS = "HARIT Platform - Onboarding request receieved";
	public static final String BODY_ONBOARDREQUEST = "Dear %s, \r\n We receieved your application for onboarding your entity. Our team will check the details, post approval you will receive the confirmation email within 7 working days. \r\n Thanks and have a good day! \r\n\r\n Regards, \r\nTeam Harit";
	public static final String SUB_ONBOARD_SUCCESS = "HARIT Platform - Onboarding Successful";
	public static final String BODY_ONBOARDSUCCESS = "Dear %s, \r\n Your application for onboarding is successfully processed. You can login to the HARIT Platform using your login name provided. \r\n Thanks and have a good day! \r\n\r\n Regards, \r\nTeam Harit";
	public static final String SUB_CLOSE_ENTITY = "HARIT Platform - Close Entity Request";
	public static final String BODY_CLOSE_ENTITY = "Dear %s, \r\n Your entity close request is successfully processed. If you didnot initiate this request, please get intouch with HARIT support desk. \r\n Thanks and have a good day! \r\n\r\n Regards, \r\nTeam Harit";
}
