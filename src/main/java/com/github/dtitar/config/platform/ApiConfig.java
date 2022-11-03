package com.github.dtitar.config.platform;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${env}/platform/${tribe}.${squad}.properties",
        "classpath:config/${env}/platform/common.properties"})
public interface ApiConfig extends Config {

    @Key("api.core.url")
    String apiCoreUrl();

    @Key("api.amplitude.dashboard.url")
    String apiAmplitudeDashboardUrl();

}
