package com.github.dtitar.api;


public enum Endpoint {

    COREAPI_AUTHENTICATE("auth/v1/authenticate"),
    AMPLITUDE_USER_SEARCH("usersearch"),
    AMPLITUDE_USER_ACTIVITY("useractivity");

    private final String endpoint;

    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getValue() {
        return endpoint;
    }
}
