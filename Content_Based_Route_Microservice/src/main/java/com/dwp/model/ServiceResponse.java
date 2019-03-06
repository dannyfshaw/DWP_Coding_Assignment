package com.dwp.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "serviceStatus", "inputRequest", "serviceMessage", })
public class ServiceResponse {
	@JsonProperty("serviceStatus")
	private boolean serviceStatus;

	@JsonProperty("inputRequest")
	private Notification inputRequest;

	@JsonProperty("serviceMessage")
	private String serviceMessage;

	@JsonProperty("serviceStatus")
	public boolean isServiceStatus() {
		return serviceStatus;
	}

	@JsonProperty("serviceStatus")
	public void setServiceStatus(boolean serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	@JsonProperty("inputRequest")
	public Notification getInputRequest() {
		return inputRequest;
	}

	@JsonProperty("inputRequest")
	public void setInputRequest(Notification inputRequest) {
		this.inputRequest = inputRequest;
	}

	@JsonProperty("serviceMessage")
	public String getServiceMessage() {
		return serviceMessage;
	}

	@JsonProperty("serviceMessage")
	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}

	public ServiceResponse() {
		super();
	}

	public ServiceResponse(boolean serviceStatus, Notification inputRequest, String serviceMessage) {
		super();
		this.serviceStatus = serviceStatus;
		this.inputRequest = inputRequest;
		this.serviceMessage = serviceMessage;
	}

}
