package com.github.dtitar.config;

import com.github.dtitar.config.platform.*;
import com.github.dtitar.config.user.UserConfig;
import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static Environment getEnv() {
        return Environment.getValueByName(getEnvironmentConfig().env());
    }

    public static DriverConfig getDriverConfig() {
        return ConfigFactory.create(DriverConfig.class, System.getProperties());
    }

    public static WebConfig getAppConfig() {
        return ConfigFactory.create(WebConfig.class, System.getProperties());
    }


    //Получение инстансов конфигов
    public static ApiConfig getApiConfig() {
        return ConfigFactory.newInstance()
                .create(ApiConfig.class);
    }

    public static ApiConfig getApiConfig(Environment environment) {
        ConfigFactory.setProperty("env", environment.getName()
                .toLowerCase());
        return ConfigFactory.create(ApiConfig.class);
    }

    public static DbConfig getDbConfig() {
        return ConfigFactory.newInstance()
                .create(DbConfig.class);
    }

    public static DbConfig getDbConfig(Environment environment) {
        ConfigFactory.setProperty("env", environment.getName()
                .toLowerCase());
        return ConfigFactory.create(DbConfig.class);
    }

    public static EnvironmentConfig getEnvironmentConfig() {
        return ConfigFactory.newInstance()
                .create(EnvironmentConfig.class);
    }

    public static UserConfig getUserConfig() {
        return ConfigFactory.newInstance().create(UserConfig.class);
    }

    public static AuthConfig getAuthConfig() {
        return ConfigFactory.newInstance().create(AuthConfig.class);
    }
}
