package com.dwp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dwp.model.Notification;
import com.dwp.service.rabbitmq.MessageSender;

@RunWith(SpringRunner.class)
@WebMvcTest(ContentBasedRouteController.class)
@WebAppConfiguration

public class ContentBasedRouteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MessageSender messageSender;
	@MockBean
	private RabbitTemplate rabbitTemplate;
	String exampleNotificationJson = "{" + "    \"serviceStatus\": true," + "    \"inputRequest\": {"
			+ "        \"documentId\": \"32b568bc-4104-4f85-b675-165c5ed18733\",\r\n"
			+ "        \"documentType\": \"UC9999\"," + "        \"documentAuthority\": \"LA0246\","
			+ "        \"documentContent\": \"Lorem ipsum dolor sit amet, consectetur adipiscing...\","
			+ "        \"additionalProperties\": {}" + "    },"
			+ "    \"serviceMessage\": \"Successfully saved request\"" + "}";

	@Test
	public void testHandleInboundRequest() throws Exception {
		Notification inputRequest = new Notification();
		inputRequest.setDocumentAuthority("LA0246");
		inputRequest.setDocumentContent("Lorem ipsum dolor sit amet, consectetur adipiscing...");
		inputRequest.setDocumentId("32b568bc-4104-4f85-b675-165c5ed18733");
		inputRequest.setDocumentType("UC9999");
		inputRequest.setAdditionalProperty(null, null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/handleInput", inputRequest)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(
				post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(inputRequest)))
				.andExpect(status().isNotFound());
	}

}
