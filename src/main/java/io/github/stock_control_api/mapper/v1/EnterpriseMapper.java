package io.github.stock_control_api.mapper.v1;

import io.github.stock_control_api.dto.v1.enterprise.EnterpriseRequestDTO;
import io.github.stock_control_api.dto.v1.enterprise.EnterpriseResponseDTO;
import io.github.stock_control_api.entity.Enterprise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {

    @Mapping(target = "id", ignore = true)
    Enterprise toEntity(EnterpriseRequestDTO dto);

    @Mapping(target = "adminId", source = "admin.id")
    @Mapping(target = "adminName", source = "admin.firstName")
    EnterpriseResponseDTO toDto(Enterprise enterprise);
}
