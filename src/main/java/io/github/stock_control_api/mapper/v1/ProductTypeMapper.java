package io.github.stock_control_api.mapper.v1;

import io.github.stock_control_api.dto.v1.producttype.ProductTypeRequestDTO;
import io.github.stock_control_api.dto.v1.producttype.ProductTypeResponseDTO;
import io.github.stock_control_api.entity.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    @Mapping(target = "id", ignore = true)
    ProductType toEntity(ProductTypeRequestDTO dto);

    ProductTypeResponseDTO toDTO(ProductType entity);
}
