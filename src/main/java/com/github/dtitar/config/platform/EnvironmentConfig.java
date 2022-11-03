package com.github.dtitar.config.platform;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("system:properties")
public interface EnvironmentConfig extends Config {

    @Key("env")
    String env();

    @Key("tribe")
    String tribe();

    @Key("squad")
    String squad();
}
