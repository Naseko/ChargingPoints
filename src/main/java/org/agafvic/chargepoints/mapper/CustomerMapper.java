package org.agafvic.chargepoints.mapper;

import org.agafvic.chargepoints.dto.CustomerDto;
import org.agafvic.chargepoints.repository.CustomerEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(target = "number", source = "number",
                    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL),
    })
    CustomerEntity mapTo(CustomerDto customerDto);

    @InheritInverseConfiguration
    CustomerDto mapTo(CustomerEntity customerEntity);
}