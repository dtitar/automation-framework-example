package com.github.dtitar.user;

import com.github.dtitar.config.ConfigHelper;
import com.github.dtitar.config.platform.Environment;

public interface UserRegistrationStrategy {
    User register(Environment env, User user);

    default User register(User user) {
        return register(ConfigHelper.getEnv(), user);
    }
}