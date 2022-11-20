package org.agafvic.chargepoints.mapper;

import org.agafvic.chargepoints.dto.ChargingPointDto;
import org.agafvic.chargepoints.repository.ChargingPointEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChargingPointMapper {
    ChargingPointMapper INSTANCE = Mappers.getMapper(ChargingPointMapper.class);

    @Mappings({
            @Mapping(target = "usn", source = "usn",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "name", source = "name",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "customer.number", source = "customerNumber",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)

    })
    ChargingPointEntity mapTo(ChargingPointDto chargingPointDto);

    @InheritInverseConfiguration
    ChargingPointDto mapTo(ChargingPointEntity chargingPointEntity);
}
