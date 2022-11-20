package org.agafvic.chargepoints.mapper;

import org.agafvic.chargepoints.dto.PatternDto;
import org.agafvic.chargepoints.repository.PatternEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatternMapper {
    PatternMapper INSTANCE = Mappers.getMapper(PatternMapper.class);

    @Mappings({
            @Mapping(target = "pattern", source = "pattern",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "format", source = "format",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    })
    PatternEntity mapTo(PatternDto customerDto);

    @InheritInverseConfiguration
    PatternDto mapTo(PatternEntity customerEntity);
}
