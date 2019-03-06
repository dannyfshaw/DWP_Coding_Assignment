
package com.dwp.model;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "documentId", "documentType", "documentAuthority", "documentContent" })
public class Notification {

	@JsonProperty("documentId")
	private String documentId;
	@JsonProperty("documentType")
	private String documentType;
	@JsonProperty("documentAuthority")
	private String documentAuthority;
	@JsonProperty("documentContent")
	private String documentContent;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("documentId")
	public String getDocumentId() {
		return documentId;
	}

	@JsonProperty("documentId")
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	@JsonProperty("documentType")
	public String getDocumentType() {
		return documentType;
	}

	@JsonProperty("documentType")
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	@JsonProperty("documentAuthority")
	public String getDocumentAuthority() {
		return documentAuthority;
	}

	@JsonProperty("documentAuthority")
	public void setDocumentAuthority(String documentAuthority) {
		this.documentAuthority = documentAuthority;
	}

	@JsonProperty("documentContent")
	public String getDocumentContent() {
		return documentContent;
	}

	@JsonProperty("documentContent")
	public void setDocumentContent(String documentContent) {
		this.documentContent = documentContent;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("documentId", documentId).append("documentType", documentType)
				.append("documentAuthority", documentAuthority).append("documentContent", documentContent)
				.append("additionalProperties", additionalProperties).toString();
	}

}
