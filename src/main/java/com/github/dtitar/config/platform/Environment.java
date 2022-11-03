package com.github.dtitar.config.platform;

import com.github.dtitar.core.exceptions.AutoTestError;
import lombok.SneakyThrows;

import java.util.Arrays;

public enum Environment {

    FT, LDS, DEV, PROD, STAGE;

    public String getName() {
        return this.name().toLowerCase();
    }

    @SneakyThrows
    public static Environment getValueByName(String name) {
        return Arrays.stream(Environment.values())
                .filter(v ->
                        name.equals(v.getName()))
                .findFirst()
                .orElseThrow(() ->
                        new AutoTestError(String.format("Unknown Env.key: '%s'", name)));
    }
}


