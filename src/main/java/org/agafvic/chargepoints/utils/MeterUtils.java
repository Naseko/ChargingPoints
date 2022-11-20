package org.agafvic.chargepoints.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MeterUtils {
    public Double mockMeterValue() {
        Random r = new Random();
        return 0.0 + (100.0 - 0.0) * r.nextDouble();
    }
}