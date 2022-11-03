package com.github.dtitar.amplitude.model.user_activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventsItem{
	private String country;
	private Data data;
	private int schema;
	@JsonProperty("device_type")
	private String deviceType;
	private String language;
	@JsonProperty("device_carrier")
	private Object deviceCarrier;
	private String uuid;
	@JsonProperty("user_creation_time")
	private String userCreationTime;
	@JsonProperty("location_lng")
	private Object locationLng;
	@JsonProperty("event_type")
	private String eventType;
	private Object paying;
	private Plan plan;
	@JsonProperty("location_lat")
	private Object locationLat;
	private int app;
	@JsonProperty("device_id")
	private String deviceId;
	@JsonProperty("global_user_properties")
	private GlobalUserProperties globalUserProperties;
	@JsonProperty("group_properties")
	private GroupProperties groupProperties;
	private Object idfa;
	@JsonProperty("server_upload_time")
	private String serverUploadTime;
	@JsonProperty("amplitude_attribution_ids")
	private Object amplitudeAttributionIds;
	@JsonProperty("server_received_time")
	private String serverReceivedTime;
	@JsonProperty("raw_event_type")
	private String rawEventType;
	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("insert_key")
	private Object insertKey;
	@JsonProperty("amplitude_event_type")
	private Object amplitudeEventType;
	private String region;
	@JsonProperty("device_model")
	private String deviceModel;
	private String city;
	@JsonProperty("user_properties")
	private UserProperties userProperties;
	@JsonProperty("start_version")
	private Object startVersion;
	private boolean isAttributionEvent;
	@JsonProperty("client_event_time")
	private String clientEventTime;
	@JsonProperty("device_family")
	private String deviceFamily;
	private String platform;
	@JsonProperty("device_manufacturer")
	private Object deviceManufacturer;
	private String library;
	private Object adid;
	@JsonProperty("partner_id")
	private Object partnerId;
	@JsonProperty("insert_id")
	private String insertId;
	@JsonProperty("client_upload_time")
	private String clientUploadTime;
	@JsonProperty("event_properties")
	private EventProperties eventProperties;
	@JsonProperty("amplitude_id")
	private long amplitudeId;
	private String os;
	@JsonProperty("os_version")
	private String osVersion;
	@JsonProperty("session_id")
	private long sessionId;
	private Groups groups;
	@JsonProperty("ip_address")
	private String ipAddress;
	@JsonProperty("event_id")
	private int eventId;
	@JsonProperty("version_name")
	private Object versionName;
	@JsonProperty("sample_rate")
	private Object sampleRate;
	@JsonProperty("device_brand")
	private Object deviceBrand;
	@JsonProperty("data_type")
	private String dataType;
	@JsonProperty("os_name")
	private String osName;
	private Object dma;
	@JsonProperty("source_id")
	private Object sourceId;
	@JsonProperty("event_time")
	private String eventTime;


	public LocalDateTime getEventTime(String pattern) {
		return LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern(pattern));
	}

	public LocalDateTime getEventTimeDefault() {
		return LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
	}


	@Override
	public String toString() {
		return "{\"EventsItem\":{"
				+ "\"country\":\"" + country + "\""
				+ ", \"data\":" + data
				+ ", \"schema\":\"" + schema + "\""
				+ ", \"deviceType\":\"" + deviceType + "\""
				+ ", \"language\":\"" + language + "\""
				+ ", \"deviceCarrier\":" + deviceCarrier
				+ ", \"uuid\":\"" + uuid + "\""
				+ ", \"userCreationTime\":\"" + userCreationTime + "\""
				+ ", \"locationLng\":" + locationLng
				+ ", \"eventType\":\"" + eventType + "\""
				+ ", \"paying\":" + paying
				+ ", \"plan\":" + plan
				+ ", \"locationLat\":" + locationLat
				+ ", \"app\":\"" + app + "\""
				+ ", \"deviceId\":\"" + deviceId + "\""
				+ ", \"globalUserProperties\":" + globalUserProperties
				+ ", \"groupProperties\":" + groupProperties
				+ ", \"idfa\":" + idfa
				+ ", \"serverUploadTime\":\"" + serverUploadTime + "\""
				+ ", \"amplitudeAttributionIds\":" + amplitudeAttributionIds
				+ ", \"serverReceivedTime\":\"" + serverReceivedTime + "\""
				+ ", \"rawEventType\":\"" + rawEventType + "\""
				+ ", \"userId\":\"" + userId + "\""
				+ ", \"insertKey\":" + insertKey
				+ ", \"amplitudeEventType\":" + amplitudeEventType
				+ ", \"region\":\"" + region + "\""
				+ ", \"deviceModel\":\"" + deviceModel + "\""
				+ ", \"city\":\"" + city + "\""
				+ ", \"userProperties\":" + userProperties
				+ ", \"startVersion\":" + startVersion
				+ ", \"isAttributionEvent\":\"" + isAttributionEvent + "\""
				+ ", \"clientEventTime\":\"" + clientEventTime + "\""
				+ ", \"deviceFamily\":\"" + deviceFamily + "\""
				+ ", \"platform\":\"" + platform + "\""
				+ ", \"deviceManufacturer\":" + deviceManufacturer
				+ ", \"library\":\"" + library + "\""
				+ ", \"adid\":" + adid
				+ ", \"partnerId\":" + partnerId
				+ ", \"insertId\":\"" + insertId + "\""
				+ ", \"clientUploadTime\":\"" + clientUploadTime + "\""
				+ ", \"eventProperties\":" + eventProperties
				+ ", \"amplitudeId\":\"" + amplitudeId + "\""
				+ ", \"os\":\"" + os + "\""
				+ ", \"osVersion\":\"" + osVersion + "\""
				+ ", \"sessionId\":\"" + sessionId + "\""
				+ ", \"groups\":" + groups
				+ ", \"ipAddress\":\"" + ipAddress + "\""
				+ ", \"eventId\":\"" + eventId + "\""
				+ ", \"versionName\":" + versionName
				+ ", \"sampleRate\":" + sampleRate
				+ ", \"deviceBrand\":" + deviceBrand
				+ ", \"dataType\":\"" + dataType + "\""
				+ ", \"osName\":\"" + osName + "\""
				+ ", \"dma\":" + dma
				+ ", \"sourceId\":" + sourceId
				+ ", \"eventTime\":\"" + eventTime + "\""
				+ "}}";
	}
}