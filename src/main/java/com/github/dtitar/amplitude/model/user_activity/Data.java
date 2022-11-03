package com.github.dtitar.amplitude.model.user_activity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data{
	@JsonProperty("group_ids")
	private GroupIds groupIds;
	@JsonProperty("group_first_event")
	private GroupFirstEvent groupFirstEvent;

	public GroupIds getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(GroupIds groupIds) {
		this.groupIds = groupIds;
	}

	public GroupFirstEvent getGroupFirstEvent() {
		return groupFirstEvent;
	}

	public void setGroupFirstEvent(GroupFirstEvent groupFirstEvent) {
		this.groupFirstEvent = groupFirstEvent;
	}

	@Override
	public String toString() {
		return "{\"Data\":{"
				+ "\"groupIds\":" + groupIds
				+ ", \"groupFirstEvent\":" + groupFirstEvent
				+ "}}";
	}
}