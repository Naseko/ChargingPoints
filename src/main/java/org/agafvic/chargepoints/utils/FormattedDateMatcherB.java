package org.agafvic.chargepoints.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class FormattedDateMatcherB implements DateMatcherB{
    @Override
    public boolean matches(String mask, String date) {
        Pattern pattern = Pattern.compile(mask);
        return pattern.matcher(date).matches();
    }
}
