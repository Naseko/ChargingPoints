package org.agafvic.chargepoints.mapper;

import org.agafvic.chargepoints.dto.SessionDto;
import org.agafvic.chargepoints.repository.ChargingSessionEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    @Mappings({
            @Mapping(target = "meter", source = "meter",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "startTime", source = "startTime",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "stopTime", source = "stopTime",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "number", source = "number",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    })
    ChargingSessionEntity mapTo(SessionDto dto);

    @InheritInverseConfiguration
    SessionDto mapTo(ChargingSessionEntity entity);

    List<SessionDto> mapTo(List<ChargingSessionEntity> carEntities);

}