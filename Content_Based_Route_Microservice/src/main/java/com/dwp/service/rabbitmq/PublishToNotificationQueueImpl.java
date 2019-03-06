package com.dwp.service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwp.config.ApplicationConfigReader;
import com.dwp.model.NotificationForQueue;

@Service
public class PublishToNotificationQueueImpl implements PublishToNotificationQueue {
	Logger logger = LoggerFactory.getLogger(PublishToNotificationQueueImpl.class);
	@Autowired
	MessageSender messageSender;
	@Autowired
	private ApplicationConfigReader applicationConfig;
	@Autowired
	RabbitTemplate rabbitTemplate;

	public PublishToNotificationQueueImpl() {

	}

	@Override
	public void sendNoficationToQueue(NotificationForQueue notificationForQueue) throws AmqpException {
		String routingKey = formatRoutingKey(notificationForQueue);
		logger.info("Before sending to Local Authority Queue routingKey::" + routingKey + "RoutingKey");
		messageSender.sendMessage(rabbitTemplate, applicationConfig.getAppExchange(), routingKey, notificationForQueue);
		logger.info("After sending to Local Authority Queue exchange");
	}

	private String formatRoutingKey(NotificationForQueue notificationForQueue) {
		StringBuffer sBuffer = new StringBuffer();
		applicationConfig.getLocalAuthourityEndpointURL();
		sBuffer.append("NOTIFICATION").append(".").append(notificationForQueue.getAuthority()).append(".")
				.append(notificationForQueue.getType());

		return sBuffer.toString();
	}

}
