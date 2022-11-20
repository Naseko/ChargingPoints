package org.agafvic.chargepoints.dto;

import lombok.Data;
import org.agafvic.chargepoints.validation.UUID;

import javax.validation.constraints.NotNull;

@Data
public class RfidBindingDto {
    @NotNull
    private String customerNumber;
    @NotNull
    private String vehicleRegPlate;
    private String name;
    @NotNull
    @UUID
    private String number;
}