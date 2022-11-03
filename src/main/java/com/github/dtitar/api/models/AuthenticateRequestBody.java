package com.github.dtitar.api.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthenticateRequestBody {

    @NonNull
    private String login;
    @NonNull
    private String password;
    private String captcha;
}
