package com.github.dtitar.steps;

import com.github.dtitar.api.models.AuthenticateRequestBody;
import com.github.dtitar.api.spec.ApiSpec;
import com.github.dtitar.config.ConfigHelper;
import com.github.dtitar.config.platform.Environment;
import com.github.dtitar.user.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.github.dtitar.api.ApiUtils.sendRequest;
import static com.github.dtitar.api.Endpoint.COREAPI_AUTHENTICATE;
import static com.github.dtitar.api.RequestType.POST;

public class ApiSteps {
    private Environment env;

    public ApiSteps() {
        this.env = ConfigHelper.getEnv();
    }

    public ApiSteps(Environment environment) {
        this.env = environment;
    }

    public Response sendAuthenticateRequest(User user) {
        RequestSpecification authenticateRequest = new ApiSpec(env)
                .request()
                .body(new AuthenticateRequestBody
                        (user.getMobilePhone(), user.getPassword()));
        Response authenticateResponse = sendRequest(authenticateRequest, POST, COREAPI_AUTHENTICATE);
        authenticateResponse.then()
                .statusCode(200);
        return authenticateResponse;
    }
}
