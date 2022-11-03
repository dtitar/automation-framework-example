package com.github.dtitar.amplitude.model.user_activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventProperties{
	@JsonProperty("page_url")
	private String pageUrl;
	private String hostname;
	@JsonProperty("page_type")
	private String pageType;
	@JsonProperty("screen_type")
	private String screenType;
	private String source;
	@JsonProperty("screen_name")
	private String screenName;
	private String status;
	@JsonProperty("registration_type")
	private String registrationType;
	@JsonProperty("landing_name")
	private String landingName;

	@Override
	public String toString() {
		return "{\"EventProperties\":{"
				+ "\"pageUrl\":\"" + pageUrl + "\""
				+ ", \"hostname\":\"" + hostname + "\""
				+ ", \"pageType\":\"" + pageType + "\""
				+ ", \"screenType\":\"" + screenType + "\""
				+ ", \"source\":\"" + source + "\""
				+ ", \"screenName\":\"" + screenName + "\""
				+ ", \"status\":\"" + status + "\""
				+ ", \"registrationType\":\"" + registrationType + "\""
				+ ", \"landingName\":\"" + landingName + "\""
				+ "}}";
	}
}