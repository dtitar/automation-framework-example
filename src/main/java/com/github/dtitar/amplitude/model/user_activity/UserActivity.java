package com.github.dtitar.amplitude.model.user_activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserActivity {
	private Metadata metadata;
	private UserData userData;
	private List<EventsItem> events;

	public EventsItem getEvent(String eventType) {
		EventsItem eventByType = events
				.stream()
				.filter(e -> e.getEventType()
						.equals(eventType))
				.findFirst()
				.orElse(null);
		return eventByType;
	}

	@Override
	public String toString() {
		return "{\"UserActivity\":{"
				+ "\"metadata\":" + metadata
				+ ", \"userData\":" + userData
				+ ", \"events\":" + events
				+ "}}";
	}
}