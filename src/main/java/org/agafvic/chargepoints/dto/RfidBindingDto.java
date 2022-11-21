package org.agafvic.chargepoints.dto;

import lombok.Data;
import org.agafvic.chargepoints.validation.UUID;

import javax.validation.constraints.NotNull;

@Data
public class RfidBindingDto {
    @NotNull @UUID
    private String customerNumber;
    @NotNull
    private String vehicleRegPlate;
    @NotNull @UUID
    private String number;
    @NotNull @UUID
    private String chargingPointUsn;
    private String name;
}