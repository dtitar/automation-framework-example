package com.github.dtitar.config.platform;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${env}/platform/${tribe}.${squad}.properties",
        "classpath:config/${env}/platform/common.properties"})
public interface DbConfig extends Config {

    @Key("db.mssql.sitestorage.name")
    String dbMssqlSiteStorageName();

    @Key("db.mssql.oscaryard.name")
    String dbMssqlOscaryardName();

    @Key("db.mssql.driver.class")
    String dbMssqlDriverClass();

    @Key("db.mssql.user")
    String dbMssqlUser();

    @Key("db.mssql.password")
    String dbMssqlPassword();

    @Key("db.mssql.host")
    String dbMssqlHost();

    @Key("db.mssql.port")
    Integer dbMssqlPort();
}
