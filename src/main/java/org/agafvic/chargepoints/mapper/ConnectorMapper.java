package org.agafvic.chargepoints.mapper;

import org.agafvic.chargepoints.dto.ConnectorDto;
import org.agafvic.chargepoints.repository.ConnectorEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConnectorMapper {
    ConnectorMapper INSTANCE = Mappers.getMapper(ConnectorMapper.class);

    @Mappings({
            @Mapping(target = "number", source = "number"),
    })
    ConnectorEntity mapTo(ConnectorDto connectorDto);

    @InheritInverseConfiguration
    ConnectorDto mapTo(ConnectorEntity connectorEntity);
}
