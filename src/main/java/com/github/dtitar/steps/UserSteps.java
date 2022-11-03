package com.github.dtitar.steps;

import com.github.dtitar.config.ConfigHelper;
import com.github.dtitar.config.platform.Environment;
import com.github.dtitar.user.User;

public class UserSteps {

    private Environment environment;
    private ApiSteps apiSteps;

    public UserSteps() {
        this.environment = ConfigHelper.getEnv();
        this.apiSteps = new ApiSteps();
    }

    public UserSteps(Environment environment) {
        this.environment = environment;
        this.apiSteps = new ApiSteps();
    }

    public String getAuthToken(User user) {
        if (user.getAuthToken() == null) {
            return apiSteps.sendAuthenticateRequest(user)
                    .getBody()
                    .path("meta.token");
        } else return user.getAuthToken();
    }
}