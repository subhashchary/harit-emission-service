/**
 * 
 */
package com.threecortex.harit.haritemissionservice.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.dto.EmailObject;

/**
 * 
 */
@Service
public interface EmailSenderService {

	CompletableFuture<String> sendEmail(EmailObject emailObject);

}
