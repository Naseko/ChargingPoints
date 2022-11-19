package org.agafvic.chargepoints.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SessionStoperDto {

    @NotNull
    private String number;
}
