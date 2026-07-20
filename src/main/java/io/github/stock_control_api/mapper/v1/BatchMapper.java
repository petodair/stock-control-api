package io.github.stock_control_api.mapper.v1;

import io.github.stock_control_api.dto.v1.batch.BatchRequestDTO;
import io.github.stock_control_api.dto.v1.batch.BatchResponseDTO;
import io.github.stock_control_api.entity.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BatchMapper {

    @Mapping(target = "id", ignore = true)
    Batch toDto(BatchRequestDTO dto);

    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productCode", source = "product.code")
    @Mapping(target = "productType", source = "product.productType.name")
    BatchResponseDTO toDto(Batch batch);
}
