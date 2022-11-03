package com.github.dtitar.api.spec;

import com.github.dtitar.config.ConfigHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class AmplitudeDashboardApiSpec {

    RequestSpecification request;
    public AmplitudeDashboardApiSpec() {
        request = new RequestSpecBuilder()
                .setBaseUri(ConfigHelper.getApiConfig()
                        .apiAmplitudeDashboardUrl())
                .setBasePath("/api/2/")
                .setContentType(ContentType.JSON)
                .setConfig(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .addHeader("Authorization", "Basic " + ConfigHelper.getAuthConfig()
                        .amplitudeAuthKey())
                .addFilters(Arrays.asList(new ResponseLoggingFilter(), new RequestLoggingFilter()))
                .build().relaxedHTTPSValidation();
    }
}
