package br.com.stock_control_api.service.product;

import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.dto.product.ProductResponseDTO;
import br.com.stock_control_api.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(ProductRequestDTO product);
    Product update(Long id, ProductRequestDTO dto);
    List<ProductResponseDTO> findAll();
    ProductResponseDTO findById(Long id);
    void deleteById(Long id);
}
