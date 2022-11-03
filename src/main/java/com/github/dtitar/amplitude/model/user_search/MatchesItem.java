package com.github.dtitar.amplitude.model.user_search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchesItem{
	private String country;
	@JsonProperty("amplitude_id")
	private long amplitudeId;
	@JsonProperty("last_seen")
	private String lastSeen;
	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("last_device_id")
	private String lastDeviceId;
	private String platform;

	@Override
	public String toString() {
		return "{\"MatchesItem\":{"
				+ "\"country\":\"" + country + "\""
				+ ", \"amplitudeId\":\"" + amplitudeId + "\""
				+ ", \"lastSeen\":\"" + lastSeen + "\""
				+ ", \"userId\":\"" + userId + "\""
				+ ", \"lastDeviceId\":\"" + lastDeviceId + "\""
				+ ", \"platform\":\"" + platform + "\""
				+ "}}";
	}
}