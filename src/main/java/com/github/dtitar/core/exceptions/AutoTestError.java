package com.github.dtitar.core.exceptions;

public class AutoTestError extends Error {
    public AutoTestError(String s, Throwable cause) {
        super(s, cause);
    }

    public AutoTestError(String s) {
        super(s);
    }
}
