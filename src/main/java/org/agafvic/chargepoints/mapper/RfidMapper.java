package org.agafvic.chargepoints.mapper;

import org.agafvic.chargepoints.dto.RfidBindingDto;
import org.agafvic.chargepoints.dto.RfidDto;
import org.agafvic.chargepoints.repository.RfidEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RfidMapper {
    RfidMapper INSTANCE = Mappers.getMapper(RfidMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "number", source = "number",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "customer.number", source = "customerNumber",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "vehicle.regPlate", source = "vehicleRegPlate",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
    })
    RfidEntity mapTo(RfidBindingDto rfidDto);
    @InheritInverseConfiguration
    RfidDto mapTo(RfidEntity rfidEntity);
}
