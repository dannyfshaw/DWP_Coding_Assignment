
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
@JsonPropertyOrder({ "reference", "type", "authority", "content" })
public class NotificationForQueue {

	@JsonProperty("reference")
	private String reference;
	@JsonProperty("type")
	private String type;
	@JsonProperty("authority")
	private String authority;
	@JsonProperty("content")
	private String content;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("reference")
	public String getReference() {
		return reference;
	}

	@JsonProperty("reference")
	public void setReference(String reference) {
		this.reference = reference;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("authority")
	public String getAuthority() {
		return authority;
	}

	@JsonProperty("authority")
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@JsonProperty("content")
	public String getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
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
		return new ToStringBuilder(this).append("reference", reference).append("type", type)
				.append("authority", authority).append("content", content)
				.append("additionalProperties", additionalProperties).toString();
	}

}
