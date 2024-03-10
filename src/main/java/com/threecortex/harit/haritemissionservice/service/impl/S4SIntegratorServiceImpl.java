/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.threecortex.harit.haritemissionservice.common.HaritContstants;
import com.threecortex.harit.haritemissionservice.dto.HaritServiceReponse;
import com.threecortex.harit.haritemissionservice.dto.SignupRequest;
import com.threecortex.harit.haritemissionservice.dto.ValidateEntityRequest;
import com.threecortex.harit.haritemissionservice.service.S4SIntegratorService;
import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
/**
 * 
 */
@Service
public class S4SIntegratorServiceImpl implements S4SIntegratorService{
	private static final Logger logger = LoggerFactory.getLogger(S4SIntegratorServiceImpl.class);
	private static final String APPLICATION_SCIM_JSON_VALUE = "application/scim+json";

	private static WebClient webClient = null;

	@Value("${s4s.uri}")
	private String s4sUri;

	@Override
	public HaritServiceReponse createUserInS4S(SignupRequest createUserRequest) {
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS); 
		logger.info("s4sURI:::{}",s4sUri);
		HttpStatusCode status = null ;

		instantiateWebclientForS4S();

		ResponseSpec userResponse = webClient.post().uri("/api/auth/signup")
				.header(HttpHeaders.CONTENT_TYPE, APPLICATION_SCIM_JSON_VALUE)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(createUserRequest),SignupRequest.class).retrieve()
				;

		try {
			status = userResponse.toBodilessEntity().block().getStatusCode();
			logger.info("status ::::{}",status.is2xxSuccessful());
		}catch (Exception e) {
			logger.info("Caught the exception in calling S4S service check the logs of S4S:::");
			//responseMessage = SeedToHarvestConstants.FAILED_S4S_SERVICE;
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			apiResponse.setResponseMessage(HaritContstants.FAILED_S4S_SERVICE);
			return apiResponse;
		}
		return apiResponse;
	}

	private void instantiateWebclientForS4S() {

		//if(webClient == null) {
		webClient = WebClient.builder().baseUrl(s4sUri)
				.filter(logRequest()).build();
		//}
	}

	/* Log request headers.
	 *
	 * @return the exchange filter function
	 */
	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			logger.info("Printing headers for : {} {}", clientRequest.method(), clientRequest.url());

			clientRequest.headers()
			.forEach((name, values) -> values.forEach(value -> logger.info("{} {}", name, value)));
			return Mono.just(clientRequest);
		});
	}

	@Override
	public HaritServiceReponse deActivateUserInS4S(ValidateEntityRequest enityRequest) {
		HaritServiceReponse apiResponse = new HaritServiceReponse(HaritContstants.API_RESPONSE_SUCCESS); 
		logger.info("s4sURI:::{}",s4sUri);
		HttpStatusCode status = null ;

		instantiateWebclientForS4S();

		ResponseSpec userResponse = webClient.post().uri("/api/auth/deactivateuser")
				.header(HttpHeaders.CONTENT_TYPE, APPLICATION_SCIM_JSON_VALUE)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(enityRequest),ValidateEntityRequest.class).retrieve()
				;

		try {
			status = userResponse.toBodilessEntity().block().getStatusCode();
			logger.info("status ::::{}",status.is2xxSuccessful());
		}catch (Exception e) {
			logger.info("Caught the exception in calling S4S service check the logs of S4S:::");
			//responseMessage = SeedToHarvestConstants.FAILED_S4S_SERVICE;
			apiResponse.setResponseStatus(HaritContstants.API_RESPONSE_FAILURE);
			apiResponse.setResponseMessage(HaritContstants.FAILED_S4S_SERVICE);

			return apiResponse;
		}
		return apiResponse;
	}


}
