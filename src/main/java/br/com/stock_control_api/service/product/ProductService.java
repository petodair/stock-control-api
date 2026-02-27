package br.com.stock_control_api.service.product;

import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.dto.product.ProductResponseDTO;
import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.enums.MeatType;
import br.com.stock_control_api.enums.StorageType;

import java.util.List;

public interface ProductService {
    ProductResponseDTO save(ProductRequestDTO product);
    ProductResponseDTO update(Long id, ProductRequestDTO dto);
    List<ProductResponseDTO> findAll(String name, String code, MeatType meatType, StorageType storageType);
    ProductResponseDTO findById(Long id);
    void deleteById(Long id);
}
