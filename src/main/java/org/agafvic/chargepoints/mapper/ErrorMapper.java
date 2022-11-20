package org.agafvic.chargepoints.mapper;

import org.agafvic.chargepoints.dto.ErrorDto;
import org.agafvic.chargepoints.dto.VehicleDto;
import org.agafvic.chargepoints.repository.ErrorEntity;
import org.agafvic.chargepoints.repository.VehicleEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ErrorMapper {
    ErrorMapper INSTANCE = Mappers.getMapper(ErrorMapper.class);

    @Mappings({
            @Mapping(target = "msg", source = "msg",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "number", source = "number",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    })
    ErrorEntity mapTo(ErrorDto errorDto);

    @InheritInverseConfiguration
    ErrorDto mapTo(ErrorEntity errorEntity);
}
