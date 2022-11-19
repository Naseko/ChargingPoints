package org.agafvic.chargepoints.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class SessionDto {
    double meter;
    Instant startTime;
    Instant stopTime;
    String number;
}