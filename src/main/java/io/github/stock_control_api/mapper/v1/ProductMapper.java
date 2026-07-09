package io.github.stock_control_api.mapper.v1;

import io.github.stock_control_api.dto.v1.product.ProductRequestDTO;
import io.github.stock_control_api.dto.v1.product.ProductResponseDTO;
import io.github.stock_control_api.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    ProductResponseDTO toDto(Product product);

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductRequestDTO dto);
}
