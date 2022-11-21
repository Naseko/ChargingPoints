package org.agafvic.chargepoints.service;

import lombok.extern.slf4j.Slf4j;
import org.agafvic.chargepoints.dto.*;
import org.agafvic.chargepoints.exceptions.EntityDoesNotExistException;
import org.agafvic.chargepoints.mapper.*;
import org.agafvic.chargepoints.repository.*;
import org.agafvic.chargepoints.utils.ConnectorUtils;
import org.apache.commons.collections4.IterableUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private ChargingPointRepository chargingPointRepository;
    @Autowired
    private ConnectorRepository connectorRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RfidRepository rfidRepository;
    @Autowired
    private ChargingSessionRepository sessionRepository;
    @Autowired
    private ErrorRepository errorRepository;
    @Autowired
    private ConnectorUtils connectorUtils;

    private ConnectorEntity getConnectorEntity(ConnectorPointBindingDto binding)
            throws EntityDoesNotExistException {

        String chargingPointUsn = binding.getChargingPointUsn();
        ChargingPointEntity chargingPointEntity =
                chargingPointRepository.findByUsn(chargingPointUsn)
                        .orElseThrow(() -> new EntityDoesNotExistException(
                                ChargingPointEntity.class, "usn", chargingPointUsn));

        return connectorUtils.createConnectorEntity(binding, chargingPointEntity);
    }

    private RfidEntity getRfid(RfidBindingDto binding) throws EntityDoesNotExistException {
        String customerNumber = binding.getCustomerNumber();
        CustomerEntity customer = customerRepository.findByNumber(customerNumber).orElseThrow(() ->
                new EntityDoesNotExistException(CustomerEntity.class, "id", customerNumber));
        if(chargingPointRepository.existsByUsn(binding.getChargingPointUsn(), customerNumber)){
            String regPlate = binding.getVehicleRegPlate();
            VehicleEntity vehicle = vehicleRepository.findByRegPlate(regPlate).orElseThrow(() ->
                    new EntityDoesNotExistException(VehicleEntity.class, "id", regPlate));

            return getRfidEntity(binding, customer, vehicle);
        }else{
            throw new EntityDoesNotExistException(CustomerEntity.class, "id", customerNumber);
        }
    }

    @NotNull
    private static RfidEntity getRfidEntity(RfidBindingDto binding, CustomerEntity customer, VehicleEntity vehicle) {
        RfidEntity rfidEntity = new RfidEntity();
        rfidEntity.setName(binding.getName());
        rfidEntity.setNumber(binding.getNumber());
        rfidEntity.setCustomer(customer);
        rfidEntity.setVehicle(vehicle);
        return rfidEntity;
    }

    public ConnectorDto createConnector(ConnectorPointBindingDto binding) {
        ConnectorEntity connectorEntity = this.getConnectorEntity(binding);
        connectorEntity = connectorRepository.save(connectorEntity);
        return ConnectorMapper.INSTANCE.mapTo(connectorEntity);
    }

    public ChargingPointDto createPoint(ChargingPointDto chargingPointDto) {
        if (null == chargingPointDto.getUsn() || chargingPointDto.getUsn().isBlank()) {
            chargingPointDto.setUsn(UUID.randomUUID().toString());
        }
        CustomerEntity customer =
                customerRepository.findByNumber(chargingPointDto.getCustomerNumber()).orElseThrow(() ->
                        new EntityDoesNotExistException(
                                CustomerEntity.class, "id", chargingPointDto.getCustomerNumber()));
        ChargingPointEntity chargingPointEntity = ChargingPointMapper.INSTANCE.mapTo(chargingPointDto);
        chargingPointEntity.setCustomer(customer);
        chargingPointEntity = chargingPointRepository.save(chargingPointEntity);
        return ChargingPointMapper.INSTANCE.mapTo(chargingPointEntity);
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        if (null == customerDto.getNumber() || customerDto.getNumber().isBlank()) {
            customerDto.setNumber(UUID.randomUUID().toString());
        }
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.mapTo(customerDto);
        customerEntity = customerRepository.save(customerEntity);
        return CustomerMapper.INSTANCE.mapTo(customerEntity);
    }

    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        VehicleEntity vehicle = VehicleMapper.INSTANCE.mapTo(vehicleDto);
        vehicle = vehicleRepository.save(vehicle);
        return VehicleMapper.INSTANCE.mapTo(vehicle);
    }

    public RfidDto createRfid(RfidBindingDto binding) {
        RfidEntity rfidEntity = this.getRfid(binding);
        rfidEntity = rfidRepository.save(rfidEntity);
        return RfidMapper.INSTANCE.mapTo(rfidEntity);
    }

    public List<SessionDto> findAllSessionsInRange(TimeRangeDto dto) {
//        List<ChargingSessionEntity> sessionEntities = IterableUtils.toList(sessionRepository.findAll());
        List<ChargingSessionEntity> sessionEntities = sessionRepository.findAllInRange(dto.getFrom(), dto.getTo());
        return SessionMapper.INSTANCE.mapTo(sessionEntities);
    }

    public SessionDto mockError(ErrorDto errorDto) {
        String number = errorDto.getNumber();
        ChargingSessionEntity session =
                sessionRepository.findByNumber(number)
                        .orElseThrow(() -> new EntityDoesNotExistException(
                                ChargingSessionEntity.class, "number", number));
        if (null != session.getStopTime()) {
            System.out.println("Stop time is: " + session.getStopTime());
            throw new EntityDoesNotExistException(ChargingSessionEntity.class, "number", number);
        } else {
            ErrorEntity errorEntity = ErrorMapper.INSTANCE.mapTo(errorDto);
            session.setStopTime(Instant.now());
            session.setError(errorEntity);
            errorEntity.setSession(session);
            errorRepository.save(errorEntity);
            return SessionMapper.INSTANCE.mapTo(session);
        }
    }
}