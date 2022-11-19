package org.agafvic.chargepoints.dto;

import lombok.Data;
import org.agafvic.chargepoints.validation.UUID;

import javax.validation.constraints.NotNull;

@Data
public class ConnectorDto {
    @NotNull @UUID
    private String number;
}
