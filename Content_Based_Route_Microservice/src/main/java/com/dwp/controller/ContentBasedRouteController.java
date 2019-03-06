package com.dwp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dwp.config.ApplicationConfigReader;
import com.dwp.model.LocalAuthorityServiceResponse;
import com.dwp.model.Notification;
import com.dwp.model.NotificationForQueue;
import com.dwp.model.ServiceResponse;
import com.dwp.service.rabbitmq.MessageSender;
import com.dwp.service.rabbitmq.PublishToNotificationQueue;

@RestController
public class ContentBasedRouteController {
	Logger logger = LoggerFactory.getLogger(ContentBasedRouteController.class);
	@Autowired
	MessageSender messageSender;
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	PublishToNotificationQueue pubishToNotificationQueue;
	@Autowired
	private ApplicationConfigReader applicationConfig;

	@PostMapping(path = "/handleInput", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse handleInboundRequest(@RequestBody Notification inputRequest) {
		logger.info("In the handleInboundRequest endpoint with inputRequest::" + inputRequest.toString());
		boolean queueResponse = false;
		try {
			messageSender.sendMessage(rabbitTemplate, applicationConfig.getAppExchange(),
					applicationConfig.getAppRoutingKey(), inputRequest);
		} catch (Exception ex) {
			logger.error("Exceptionfrom sendMessage::" + ex.getMessage());
		}
		logger.info("Queue response::" + queueResponse);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setInputRequest(inputRequest);
		serviceResponse.setServiceStatus(true);
		serviceResponse.setServiceMessage("Successfully saved request");
		return serviceResponse;
	}

	@PostMapping(path = "/publishToLocalAuthorityQueue", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LocalAuthorityServiceResponse forwardToLocalAuthorityQueue(
			@RequestBody NotificationForQueue notificationForQueue) {
		LocalAuthorityServiceResponse localAuthorityServiceResponse = new LocalAuthorityServiceResponse();
		localAuthorityServiceResponse.setInputRequest(notificationForQueue);
		logger.info("In controller about to send to local Authority queue");
		try {
			pubishToNotificationQueue.sendNoficationToQueue(notificationForQueue);
			localAuthorityServiceResponse.setServiceRequestSuccess(true);
			localAuthorityServiceResponse.setServiceMessage("Successfully sent to queue");
		} catch (AmqpException ex) {
			logger.warn("Exception sending notificationForQueue tyoLoca AuthorityQueue ex::" + ex.getMessage());
			localAuthorityServiceResponse.setServiceRequestSuccess(false);
			localAuthorityServiceResponse.setServiceMessage(ex.getMessage());
		}
		return localAuthorityServiceResponse;
	}

}
