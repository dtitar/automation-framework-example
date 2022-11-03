package com.github.dtitar.user;

import com.github.dtitar.config.platform.Environment;
import com.github.dtitar.steps.UserSteps;

public class OnlineUserRegistrationStrategy implements UserRegistrationStrategy {

    @Override
    public User register(Environment env, User user) {
        UserSteps coreUserSteps = new UserSteps(env);
//        todo: implement full user registration process with api if needed
//        userSteps.setPhone(user);
//        userSteps.setCode(user);
//        userSteps.checkOnlineAccount(user);
//        userSteps.registerUser(user);
//        userSteps.waitUntilMobileConfirmed(user);
//        userSteps.confirmEmail(user);
//        userSteps.bindAccount(user);
//        userSteps.sendIdentificationSms(user);
//        userSteps.passIdentification(user);
        return user;
    }
}