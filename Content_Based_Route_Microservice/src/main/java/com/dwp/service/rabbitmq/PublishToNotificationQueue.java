package com.dwp.service.rabbitmq;

import org.springframework.amqp.AmqpException;

import com.dwp.model.NotificationForQueue;

public interface PublishToNotificationQueue {
	public void sendNoficationToQueue(NotificationForQueue notification) throws AmqpException;
}
