package com.github.dtitar.amplitude.assertions;

import com.github.dtitar.amplitude.model.user_activity.EventsItem;
import org.assertj.core.api.AbstractAssert;

import java.time.LocalDateTime;
import java.util.Objects;

public class EventsItemAssert extends AbstractAssert<EventsItemAssert, EventsItem> {
    protected EventsItemAssert(EventsItem actual) {
        super(actual, EventsItemAssert.class);
    }

    public EventsItemAssert hasEventType(String eventType) {
        isNotNull();
        if (!Objects.equals(actual.getEventType(), eventType)) {
            failWithMessage("Expected eventType to be <%s> but was <%s>", eventType, actual.getEventType());
        }
        return this;
    }

    public EventsItemAssert hasHostNameEventProperty(String hostName) {
        isNotNull();
        if (!Objects.equals(actual.getEventProperties().getHostname(), hostName)) {
            failWithMessage("Expected event property 'hostName' to be <%s> but was <%s>", hostName, actual.getEventProperties().getHostname());
        }
        return this;
    }

    public EventsItemAssert hasLandingNameEventProperty(String landingName) {
        isNotNull();
        if (!Objects.equals(actual.getEventProperties().getLandingName(), landingName)) {
            failWithMessage("Expected event property 'landingName' to be <%s> but was <%s>", landingName, actual.getEventProperties().getLandingName());
        }
        return this;
    }

    public EventsItemAssert isEventTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        isNotNull();
        if (!actual.getEventTimeDefault().isAfter(startTime) && actual.getEventTimeDefault().isBefore(endTime)) {
            failWithMessage("Expected eventTime <%s> is between <%s> and <%s> but it isn't", actual.getEventTime(), startTime.toString(), endTime.toString());
        }
        return this;
    }
}
