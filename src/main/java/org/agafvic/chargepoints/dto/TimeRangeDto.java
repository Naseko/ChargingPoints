package org.agafvic.chargepoints.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TimeRangeDto {
    OffsetDateTime from;
    OffsetDateTime to;
}
