package com.github.dtitar.amplitude.model.user_activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData{
	private String country;
	@JsonProperty("device_ids")
	private List<String> deviceIds;
	@JsonProperty("start_version")
	private String startVersion;
	private String city;
	@JsonProperty("last_used")
	private String lastUsed;
	private String language;
	@JsonProperty("device_type")
	private String deviceType;
	@JsonProperty("merge_times")
	private MergeTimes mergeTimes;
	private String platform;
	private int revenue;
	private String library;
	private String paying;
	@JsonProperty("last_device_id")
	private String lastDeviceId;
	private String os;
	private int purchases;
	private long firstSeen;
	@JsonProperty("canonical_amplitude_id")
	private long canonicalAmplitudeId;
	private int usageTime;
	@JsonProperty("ip_address")
	private String ipAddress;
	private String version;
	@JsonProperty("merged_amplitude_ids")
	private List<Object> mergedAmplitudeIds;
	@JsonProperty("last_location")
	private LastLocation lastLocation;
	private String carrier;
	private long lastSeen;
	@JsonProperty("user_id")
	private int userId;
	private String dma;
	private int lastLocationLong;
	private String region;
	private String device;
	private int lastLocationLat;
	private Properties properties;

	@Override
	public String toString() {
		return "{\"UserData\":{"
				+ "\"country\":\"" + country + "\""
				+ ", \"deviceIds\":" + deviceIds
				+ ", \"startVersion\":\"" + startVersion + "\""
				+ ", \"city\":\"" + city + "\""
				+ ", \"lastUsed\":\"" + lastUsed + "\""
				+ ", \"language\":\"" + language + "\""
				+ ", \"deviceType\":\"" + deviceType + "\""
				+ ", \"mergeTimes\":" + mergeTimes
				+ ", \"platform\":\"" + platform + "\""
				+ ", \"revenue\":\"" + revenue + "\""
				+ ", \"library\":\"" + library + "\""
				+ ", \"paying\":\"" + paying + "\""
				+ ", \"lastDeviceId\":\"" + lastDeviceId + "\""
				+ ", \"os\":\"" + os + "\""
				+ ", \"purchases\":\"" + purchases + "\""
				+ ", \"firstSeen\":\"" + firstSeen + "\""
				+ ", \"canonicalAmplitudeId\":\"" + canonicalAmplitudeId + "\""
				+ ", \"usageTime\":\"" + usageTime + "\""
				+ ", \"ipAddress\":\"" + ipAddress + "\""
				+ ", \"version\":\"" + version + "\""
				+ ", \"mergedAmplitudeIds\":" + mergedAmplitudeIds
				+ ", \"lastLocation\":" + lastLocation
				+ ", \"carrier\":\"" + carrier + "\""
				+ ", \"lastSeen\":\"" + lastSeen + "\""
				+ ", \"userId\":\"" + userId + "\""
				+ ", \"dma\":\"" + dma + "\""
				+ ", \"lastLocationLong\":\"" + lastLocationLong + "\""
				+ ", \"region\":\"" + region + "\""
				+ ", \"device\":\"" + device + "\""
				+ ", \"lastLocationLat\":\"" + lastLocationLat + "\""
				+ ", \"properties\":" + properties
				+ "}}";
	}
}