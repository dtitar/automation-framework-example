package com.github.dtitar.amplitude.model.user_search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSearch{
	private String type;
	private List<MatchesItem> matches;

	@Override
	public String toString() {
		return "{\"UserSearch\":{"
				+ "\"type\":\"" + type + "\""
				+ ", \"matches\":" + matches
				+ "}}";
	}
}