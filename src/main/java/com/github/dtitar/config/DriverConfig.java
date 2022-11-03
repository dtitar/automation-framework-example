package com.github.dtitar.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/remote.properties",
        "classpath:config/local.properties"
})
public interface DriverConfig extends Config {
    @DefaultValue("chrome")
    String browser();
    String browserVersion();

    String browserSize();

    @DefaultValue("false")
    String remoteDriver();
    String remoteIP();

    String remotePort();

    String videoStorage();
}
