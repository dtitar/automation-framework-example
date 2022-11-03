package com.github.dtitar.user;

import com.github.dtitar.config.platform.Environment;
import com.github.dtitar.steps.UserSteps;

public class OnlineUserRegistrationWithoutIdentificationStrategy implements UserRegistrationStrategy {
    @Override
    public User register(Environment env, User user) {
        UserSteps coreUserSteps = new UserSteps(env);
//        todo: implement partial user registration process with api if needed
//        coreUserSteps.setPhone(user);
//        coreUserSteps.setCode(user);
//        coreUserSteps.checkOnlineAccount(user);
//        coreUserSteps.registerUser(user);
//        coreUserSteps.waitUntilMobileConfirmed(user);
//        coreUserSteps.confirmEmail(user);
        return user;
    }
}