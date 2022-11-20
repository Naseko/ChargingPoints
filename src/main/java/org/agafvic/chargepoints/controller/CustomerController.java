package org.agafvic.chargepoints.controller;

import org.agafvic.chargepoints.dto.SessionDto;
import org.agafvic.chargepoints.dto.SessionStarterDto;
import org.agafvic.chargepoints.dto.SessionStoperDto;
import org.agafvic.chargepoints.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/api/v1/customer/session/start")
    public SessionDto startSession(@Validated @RequestBody SessionStarterDto sessionStarterDto)
            throws EntityNotFoundException {
        return customerService.startSession(sessionStarterDto);
    }

    @PostMapping("/api/v1/customer/session/stop")
    public SessionDto stopSession(@Validated @RequestBody SessionStoperDto sessionStoperDto)
            throws EntityNotFoundException {
        return customerService.stopSession(sessionStoperDto);
    }
}