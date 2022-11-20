package org.agafvic.chargepoints.service;

import org.agafvic.chargepoints.dto.SessionDto;
import org.agafvic.chargepoints.dto.SessionStarterDto;
import org.agafvic.chargepoints.dto.SessionStoperDto;
import org.agafvic.chargepoints.exceptions.EntityDoesNotExistException;
import org.agafvic.chargepoints.mapper.SessionMapper;
import org.agafvic.chargepoints.repository.*;
import org.agafvic.chargepoints.utils.MeterUtils;
import org.agafvic.chargepoints.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private ChargingPointRepository chargingPointRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RfidRepository rfidRepository;
    @Autowired
    private ChargingSessionRepository chargingSessionRepository;
    @Autowired
    private MeterUtils meterUtils;
    @Autowired
    private SessionUtils sessionUtils;

    public SessionDto startSession(SessionStarterDto sessionStarterDto) {
        String rfidNumber = sessionStarterDto.getRfidNumber();
        RfidEntity rfid =
                rfidRepository.findByNumber(sessionStarterDto.getRfidNumber())
                        .orElseThrow(() -> new EntityDoesNotExistException(
                                RfidEntity.class, "number", rfidNumber));

        if (customerRepository.existsById(rfid.getCustomer().getId())) {
            ChargingSessionEntity session = sessionUtils.getSession(rfid);
            ChargingSessionEntity savedEntity = chargingSessionRepository.save(session);
            return SessionMapper.INSTANCE.mapTo(savedEntity);
        } else {
            throw new EntityDoesNotExistException(
                    CustomerEntity.class, "id", rfid.getCustomer().getId().toString());
        }
    }

    public SessionDto stopSession(SessionStoperDto sessionStoperDto) {
        String number = sessionStoperDto.getNumber();
        ChargingSessionEntity session =
                chargingSessionRepository.findByNumber(number)
                        .orElseThrow(() -> new EntityDoesNotExistException(
                                ChargingSessionEntity.class, "number", number));
        if (null != session.getStopTime()) {
            throw new EntityDoesNotExistException(ChargingSessionEntity.class, "number", number);
        } else {
            session.setStopTime(Instant.now());
            session.setMeter(meterUtils.mockMeterValue());
            ChargingSessionEntity savedEntity = chargingSessionRepository.save(session);
            return SessionMapper.INSTANCE.mapTo(savedEntity);
        }
    }
}