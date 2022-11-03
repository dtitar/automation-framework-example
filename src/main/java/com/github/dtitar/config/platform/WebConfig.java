package com.github.dtitar.config.platform;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${env}/app.properties"
})
public interface WebConfig extends Config {

    String startUrl();

}
