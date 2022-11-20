package org.agafvic.chargepoints.controller;

import lombok.extern.slf4j.Slf4j;
import org.agafvic.chargepoints.dto.*;
import org.agafvic.chargepoints.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "", produces = "application/json")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @PostMapping("/api/v1/admin/connector")
    public ConnectorDto createConnector(@Validated @RequestBody ConnectorPointBindingDto binding)
            throws EntityNotFoundException {
        return adminServiceImpl.createConnector(binding);
    }

    @PostMapping("/api/v1/admin/point")
    public ChargingPointDto createPoint(@Validated @RequestBody ChargingPointDto chargingPointDto)
            throws EntityNotFoundException {
        return adminServiceImpl.createPoint(chargingPointDto);
    }

    @PostMapping("/api/v1/admin/customer")
    public CustomerDto createCustomer(@Validated @RequestBody CustomerDto customer)
            throws EntityNotFoundException {
        return adminServiceImpl.createCustomer(customer);
    }

    @PostMapping("/api/v1/admin/vehicle")
    public VehicleDto createVehicle(@Validated @RequestBody VehicleDto vehicle)
            throws EntityNotFoundException {
        return adminServiceImpl.createVehicle(vehicle);
    }

    @PostMapping("/api/v1/admin/rfid")
    public RfidDto createRfid(@Validated @RequestBody RfidBindingDto rfid)
            throws EntityNotFoundException {
        return adminServiceImpl.createRfid(rfid);
    }

    @GetMapping("/api/v1/admin/sessions")
    public List<SessionDto> getSessions(@Validated @RequestBody TimeRangeDto dto)
            throws EntityNotFoundException {
        return adminServiceImpl.findAllSessionsInRange(dto);
    }

    @PostMapping("/api/v1/admin/mockerror")
    public SessionDto createMockedError(@Validated @RequestBody ErrorDto error)
            throws EntityNotFoundException {
        return adminServiceImpl.mockError(error);
    }
}