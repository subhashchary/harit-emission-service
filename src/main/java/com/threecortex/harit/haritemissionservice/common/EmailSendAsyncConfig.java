/**
 * 
 */
package com.threecortex.harit.haritemissionservice.common;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 */
@Configuration
@EnableAsync
@PropertySource("classpath:application.properties")
public class EmailSendAsyncConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailSendAsyncConfig.class);
	
	@Value("${mailSend.corepoolsize}")
	private Integer corePoolSize;
	
	@Value("${mailSend.maxpoolsize}")
	private Integer maxPoolSize;
	
	@Value("${mailSend.queuecapacity}")
	private Integer queueCapacity;
	
	
	@Bean (name = "mailSendTaskExecutor")
	public Executor mailSendTaskExecutor() {
		
		logger.info("Creating Async mailSend Task Executor");
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		
		logger.info("Configuration for Recon thread:::");
		logger.info("corePoolSize::{}",corePoolSize);
		logger.info("maxPoolSize::{}",maxPoolSize);
		logger.info("queueCapacity::{}",queueCapacity);
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("MailSendTask-");
		executor.initialize();
		return executor;
		
	}


	/**
	 * @return the corePoolSize
	 */
	public Integer getCorePoolSize() {
		return corePoolSize;
	}


	/**
	 * @param corePoolSize the corePoolSize to set
	 */
	public void setCorePoolSize(Integer corePoolSize) {
		this.corePoolSize = corePoolSize;
	}


	/**
	 * @return the maxPoolSize
	 */
	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}


	/**
	 * @param maxPoolSize the maxPoolSize to set
	 */
	public void setMaxPoolSize(Integer maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}


	/**
	 * @return the queueCapacity
	 */
	public Integer getQueueCapacity() {
		return queueCapacity;
	}


	/**
	 * @param queueCapacity the queueCapacity to set
	 */
	public void setQueueCapacity(Integer queueCapacity) {
		this.queueCapacity = queueCapacity;
	}
	
	

}
