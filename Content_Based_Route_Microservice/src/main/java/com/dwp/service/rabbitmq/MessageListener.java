package com.dwp.service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.dwp.model.NotificationForQueue;
import com.dwp.service.ApplicationConstant;
import com.dwp.service.MongoDBService;

/**
 * Message Listener for RabbitMQ
 * 
 * @author dannny.shaw
 *
 */

@Service
public class MessageListener {
	@Autowired
	private MongoDBService mongoDBService;
	private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

	public MessageListener() {
		super();
	}

	/**
	 * Message listener
	 * 
	 * @param NotificationForQueue object used for deserialization of message
	 */
	@RabbitListener(queues = "${app.queue.name}")
	public void receiveMessageForNotification(final NotificationForQueue data) {
		log.info("Received message: {} from queue.", data);

		try {
			log.info("Save notification from Queue to MongoDB. NOT YET IMPLEMENTED");
			// FIXME uncomment call to MongoDB service once it is implemented
			// mongoDBService.saveMessageToMongoDB(data);

		} catch (HttpClientErrorException ex) {

			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.info("Delay...");
				try {
					Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
				} catch (InterruptedException e) {
				}

				log.info("Throwing exception so that message will be requeued in the queue.");
				throw new RuntimeException();
			} else {
				throw new AmqpRejectAndDontRequeueException(ex);
			}

		} catch (Exception e) {
			log.error("Internal server error occurred . Bypassing message requeue {}", e);
			throw new AmqpRejectAndDontRequeueException(e);
		}

	}

}
