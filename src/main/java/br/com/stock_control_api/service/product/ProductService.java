package br.com.stock_control_api.service.product;

import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.dto.product.ProductResponseDTO;
import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.enums.ProductType;

import java.util.List;

public interface ProductService {
    Product save(ProductRequestDTO product);
    Product update(Long id, ProductRequestDTO dto);
    List<ProductResponseDTO> findAll(String name, String code, ProductType type);
    ProductResponseDTO findById(Long id);
    void deleteById(Long id);
}
