package com.github.dtitar.config.user;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${env}/users/${tribe}.${squad}.users.properties"})
public interface UserConfig extends Config {

    @DefaultValue("1")
    String index();

    @Key("user${index}.mobilePhone")
    String mobilePhone();

    @Key("user${index}.email")
    String email();

    @Key("user${index}.password")
    String password();

    @Key("user${index}.passwordCupis")
    String passwordCupis();

    @Key("user${index}.lastName")
    String lastName();

    @Key("user${index}.firstName")
    String firstName();

    @Key("user${index}.middleName")
    String middleName();

    @Key("user${index}.birthDate")
    String birthDate();

    @Key("user${index}.city")
    String city();
}