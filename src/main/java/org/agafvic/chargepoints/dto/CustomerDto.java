package org.agafvic.chargepoints.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerDto {
    @NotNull
    private String name;
    private String number;
}
