package com.github.dtitar.amplitude.model.user_activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProperties{
	private String isExperienced;
	private String age;

	@Override
	public String toString() {
		return "{\"UserProperties\":{"
				+ "\"isExperienced\":\"" + isExperienced + "\""
				+ ", \"age\":\"" + age + "\""
				+ "}}";
	}
}