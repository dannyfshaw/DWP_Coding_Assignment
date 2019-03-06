package com.dwp.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "serviceRequestSuccess",
    "inputRequest",
    "serviceMessage",
})
public class LocalAuthorityServiceResponse {
	 @JsonProperty("serviceRequestSuccess")
	private boolean serviceRequestSuccess;
	 
	 @JsonProperty("inputRequest")
	private NotificationForQueue inputRequest;
	 
	 @JsonProperty("serviceMessage")
	private String serviceMessage;
	 
	 @JsonProperty("serviceRequestSuccess")
	public boolean isServiceStatus() {
		return serviceRequestSuccess;
	}
	 @JsonProperty("serviceRequestSuccess")
	public void setServiceRequestSuccess(boolean serviceRequestSuccess) {
		this.serviceRequestSuccess = serviceRequestSuccess;
	}
	 @JsonProperty("inputRequest")
	public NotificationForQueue getInputRequest() {
		return inputRequest;
	}
	 @JsonProperty("inputRequest")
	public void setInputRequest(NotificationForQueue inputRequest) {
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
	public LocalAuthorityServiceResponse() {
		super();
	}
	public LocalAuthorityServiceResponse(boolean serviceRequestSuccess, NotificationForQueue inputRequest, String serviceMessage) {
		super();
		this.serviceRequestSuccess = serviceRequestSuccess;
		this.inputRequest = inputRequest;
		this.serviceMessage = serviceMessage;
	}
	
}
