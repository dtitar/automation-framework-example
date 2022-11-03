package com.github.dtitar.core.helpers;

import com.github.dtitar.core.exceptions.AutoTestError;
import java.util.regex.Pattern;

public class Utils {

    public static double convertToDouble(String value) {
        String s = value.replaceAll(",", ".")
                .replaceAll(" ", "")
                .replaceAll("\\u00A0", "")
                .trim();
        if (Pattern.compile("^[0-9]+(\\.[0-9]+)?$")
                .matcher(s)
                .matches()) return Double.parseDouble(s);
        throw new AutoTestError(String.format("Value %s must be digit!", s));
    }
}
