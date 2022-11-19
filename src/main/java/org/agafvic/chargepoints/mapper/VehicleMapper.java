package org.agafvic.chargepoints.mapper;

import org.agafvic.chargepoints.dto.VehicleDto;
import org.agafvic.chargepoints.repository.VehicleEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "regPlate", source = "regPlate",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    })
    VehicleEntity mapTo(VehicleDto vehicleDto);
    @InheritInverseConfiguration
    VehicleDto mapTo(VehicleEntity vehicleEntity);
}