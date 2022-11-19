package org.agafvic.chargepoints.service;

import lombok.extern.slf4j.Slf4j;
import org.agafvic.chargepoints.dto.*;
import org.agafvic.chargepoints.exceptions.EntityDoesNotExistException;
import org.agafvic.chargepoints.mapper.*;
import org.agafvic.chargepoints.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired private ChargingPointRepository chargingPointRepository;
    @Autowired private ConnectorRepository connectorRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private VehicleRepository vehicleRepository;

    @Autowired private RfidRepository rfidRepository;

    @Autowired private ChargingSessionRepository sessionRepository;

    //TODO refactor
    private ConnectorEntity getConnectorEntity(ConnectorPointBindingDto binding)
            throws EntityDoesNotExistException {

        String chargingPointUsn = binding.getChargingPointUsn();
        ChargingPointEntity chargingPointEntity =
                chargingPointRepository.findByUsn(chargingPointUsn)
                        .orElseThrow(() -> new EntityDoesNotExistException(
                                ChargingPointEntity.class, "usn", chargingPointUsn));

        ConnectorEntity connectorEntity = new ConnectorEntity();
        connectorEntity.setChargingPoint(chargingPointEntity);
        connectorEntity.setNumber(binding.getConnectorUsn());

        return connectorEntity;
    }

    private RfidEntity getRfid(RfidBindingDto binding) throws EntityDoesNotExistException {
        String customerNumber = binding.getCustomerNumber();
        CustomerEntity customer =
                customerRepository.findByNumber(customerNumber).orElseThrow(() -> new EntityDoesNotExistException(
                        CustomerEntity.class, "id", customerNumber));

        String regPlate = binding.getVehicleRegPlate();

        VehicleEntity vehicle =
                vehicleRepository.findByRegPlate(regPlate).orElseThrow(() -> new EntityDoesNotExistException(
                        VehicleEntity.class, "id", regPlate));

        //TODO заменить на маппер
        RfidEntity rfidEntity = new RfidEntity();
        rfidEntity.setName(binding.getName());
        rfidEntity.setNumber(binding.getNumber());
        rfidEntity.setCustomer(customer);
        rfidEntity.setVehicle(vehicle);
        return rfidEntity;
    }

    public ConnectorDto createConnector(ConnectorPointBindingDto binding) {
        ConnectorEntity connectorEntity = this.getConnectorEntity(binding);
        ConnectorEntity ce = connectorRepository.save(connectorEntity);
        return ConnectorMapper.INSTANCE.mapTo(ce);
    }

    public ChargingPointDto createPoint(ChargingPointDto chargingPointDto){
        if(null == chargingPointDto.getUsn() || chargingPointDto.getUsn().isBlank()){
            chargingPointDto.setUsn(UUID.randomUUID().toString());
        }
        ChargingPointEntity chargingPointEntity = ChargingPointMapper.INSTANCE.mapTo(chargingPointDto);
        CustomerEntity customer =
                customerRepository.findByNumber(chargingPointDto.getCustomerNumber()).orElseThrow(() ->
                        new EntityDoesNotExistException(
                                CustomerEntity.class, "id", chargingPointDto.getCustomerNumber()));
        chargingPointEntity.setCustomer(customer);
        ChargingPointEntity cpe = chargingPointRepository.save(chargingPointEntity);
        return ChargingPointMapper.INSTANCE.mapTo(cpe);
    }

    public CustomerDto createCustomer(CustomerDto customerDto){
        if(null == customerDto.getNumber() || customerDto.getNumber().isBlank()){
            customerDto.setNumber(UUID.randomUUID().toString());
        }
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.mapTo(customerDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return CustomerMapper.INSTANCE.mapTo(savedCustomer);
    }

    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        VehicleEntity vehicle = VehicleMapper.INSTANCE.mapTo(vehicleDto);
        VehicleEntity savedVehicle = vehicleRepository.save(vehicle);
        return VehicleMapper.INSTANCE.mapTo(savedVehicle);
    }

    public RfidDto createRfid(RfidBindingDto binding) {
        RfidEntity rfidEntity = this.getRfid(binding);
        RfidEntity savedRfid = rfidRepository.save(rfidEntity);
        return RfidMapper.INSTANCE.mapTo(savedRfid);
    }

    public List<SessionDto> findAllSessionsInRange(TimeRangeDto dto) {
        List<ChargingSessionEntity> sessionEntities =
                sessionRepository.findAllInRange(dto.getFrom(),dto.getTo());
        return SessionMapper.INSTANCE.mapTo(sessionEntities);
    }
}