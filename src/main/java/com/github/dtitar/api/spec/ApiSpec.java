package com.github.dtitar.api.spec;

import com.github.dtitar.config.ConfigHelper;
import com.github.dtitar.config.platform.Environment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ApiSpec {

    private final Environment env;

    public ApiSpec() {
        env = ConfigHelper.getEnv();
    }

    public ApiSpec(Environment env) {
        this.env = env;
    }

    public RequestSpecification request() {
        return createDefaultRequest().build();
    }

    public RequestSpecification requestWithToken(String authToken) {
        return createDefaultRequest().addHeader("x-api-cred", authToken)
                .build();
    }

    private RequestSpecBuilder createDefaultRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigHelper.getApiConfig(env)
                        .apiCoreUrl())
                .setBasePath("/rest/")
                .setContentType(ContentType.JSON)
                .addHeader("x-application-name", "desktop")
                .setConfig(RestAssured.config()
                        .headerConfig(HeaderConfig.headerConfig()
                                .overwriteHeadersWithName("x-api-cred", "x-application-name", "Content-Type")))
                .setConfig(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured()));
    }
}
