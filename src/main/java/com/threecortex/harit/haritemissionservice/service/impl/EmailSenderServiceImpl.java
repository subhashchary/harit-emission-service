/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.threecortex.harit.haritemissionservice.dto.EmailObject;
import com.threecortex.harit.haritemissionservice.service.EmailSenderService;

import reactor.core.publisher.Mono;

/**
 * 
 */
@Service
public class EmailSenderServiceImpl implements EmailSenderService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);
	
	private static final String APPLICATION_SCIM_JSON_VALUE = "application/scim+json";
	private static WebClient webClient = null;
	
	@Value("${sendmail.uri}")
	private String sendmailUri;
	
	
	@Override
	@Async("mailSendTaskExecutor")
	public CompletableFuture<String> sendEmail(EmailObject emailObject) {
		logger.info("sendmailUri: ::{}",sendmailUri);

		instantiateWebclientForSendEmail();

		String response  = webClient.post().uri("/saya-emailsender-service")
				.header(HttpHeaders.CONTENT_TYPE, APPLICATION_SCIM_JSON_VALUE)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(emailObject),EmailObject.class).retrieve()
				.bodyToMono(String.class)
				.block() //here commenting the block since recon optimizer is serverless it takes time we will get 504 status
				;
		
		logger.info("Response :::{}", response);
		return CompletableFuture.completedFuture(response);
		
	}

	private void instantiateWebclientForSendEmail() {
		webClient = WebClient.builder().baseUrl(sendmailUri)
				.filter(logRequest()).build();
		
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

}
