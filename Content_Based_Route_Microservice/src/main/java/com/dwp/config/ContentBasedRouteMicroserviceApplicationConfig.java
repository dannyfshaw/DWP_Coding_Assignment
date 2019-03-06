package com.dwp.config;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.web.client.RestTemplate;

import com.dwp.service.MongoDBService;
import com.dwp.service.MongoDBServiceImpl;
import com.dwp.service.rabbitmq.MessageSender;
import com.dwp.service.rabbitmq.PublishToNotificationQueue;
import com.dwp.service.rabbitmq.PublishToNotificationQueueImpl;

/**
 * Message listener
 * @author Danny.Shaw
 * @param ContentBasedRouteMicroserviceApplicationConfig object used to initialise beans being used in the spring boot ContentBasedRouteMicroserviceApplication
 */

@Configuration
public class ContentBasedRouteMicroserviceApplicationConfig extends SpringBootServletInitializer
		implements RabbitListenerConfigurer {
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	ConnectionFactory connectionFactory;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}

	@Bean
	public MongoDBService mongoDBServiceImpl() {
		return new MongoDBServiceImpl();
	}

	@Bean
	PublishToNotificationQueue pubishToNotificationQueue() {
		return new PublishToNotificationQueueImpl();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}
	@Bean
	public MessageSender messageSender() {
		return new MessageSender();
	}

	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
}
