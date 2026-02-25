package br.com.stock_control_api.mapper;

import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.dto.product.ProductResponseDTO;
import br.com.stock_control_api.entity.Product;

public abstract class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setCode(dto.code());
        product.setPrice(dto.price());
        product.setMeatType(dto.meatType());
        product.setStorageType(dto.storageType());
        return product;
    }

    public static Product toEntity(Long id, ProductRequestDTO dto) {
        Product product = new Product();
        product.setId(id);
        product.setName(dto.name());
        product.setCode(dto.code());
        product.setPrice(dto.price());
        product.setMeatType(dto.meatType());
        product.setStorageType(dto.storageType());
        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getPrice(),
                product.getMeatType(),
                product.getStorageType()
        );
    }

}
