package com.github.dtitar.config.platform;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("system:properties")
public interface AuthConfig extends Config {

    @Key("amplitude.auth.key")
    String amplitudeAuthKey();
}
