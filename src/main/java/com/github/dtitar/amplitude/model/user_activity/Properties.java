package com.github.dtitar.amplitude.model.user_activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties{
	private boolean isExperienced;
	private String age;
}