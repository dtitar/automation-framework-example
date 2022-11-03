package com.github.dtitar.api;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;

public class ApiUtils {

    private static final int DEFAULT_REQUEST_TIMEOUT_IN_SECONDS = 60;
    private static final int DEFAULT_REQUEST_INTERVAL_IN_SECONDS = 1;

    public static Response sendRequest(RequestSpecification requestSpecification, RequestType requestType, Endpoint endpoint) {
        return given(requestSpecification).request(requestType.name(), endpoint.getValue());
    }

    public static Response sendRequestAndWaitForExpectedResultByPath(RequestSpecification requestSpecification, RequestType reqType, Endpoint endpoint, String responsePath, String expectedValue) {
        AtomicReference<Response> response = new AtomicReference<>();
        await()
                .atMost(DEFAULT_REQUEST_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .pollInterval(DEFAULT_REQUEST_INTERVAL_IN_SECONDS, TimeUnit.SECONDS)
                .until(() -> {
                    response.set(sendRequest(requestSpecification, reqType, endpoint));
                    return getResponseValueByPath(response.get(), responsePath)
                            .equals(expectedValue);
                });
        return response.get();
    }

    public static String getResponseHeaderValueByHeaderName(Response response, String headerName) {
        return response.getHeader(headerName);
    }

    public static String getResponseBodyNodeValueByXmlPath(Response response, String xmlPath) {
        return response.getBody()
                .xmlPath(XmlPath.CompatibilityMode.HTML)
                .getString(xmlPath);
    }

    public static String getResponseValueByPath(Response response, String path) {
        return response.then()
                .extract()
                .path(path)
                .toString();
    }
}
