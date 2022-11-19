package org.agafvic.chargepoints.dto;

import lombok.Data;
import org.agafvic.chargepoints.validation.UUID;

import javax.validation.constraints.NotNull;

@Data
public class ChargingPointDto {
    @UUID
    private String usn;
    @NotNull
    private String customerNumber;
    @NotNull
    private String name;
}
