package org.agafvic.chargepoints.dto;

import lombok.Data;
import org.agafvic.chargepoints.validation.UUID;

import javax.validation.constraints.NotNull;

@Data
public class SessionStarterDto {
    @NotNull
    private String rfidNumber;
    @NotNull
    @UUID
    private String chargingPointUSN;
}