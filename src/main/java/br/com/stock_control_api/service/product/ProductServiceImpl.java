package br.com.stock_control_api.service.product;

import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.dto.product.ProductResponseDTO;
import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.mapper.ProductMapper;
import br.com.stock_control_api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(ProductRequestDTO dto) {
        return this.productRepository.save(ProductMapper.toEntity(dto));
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        List<Product> products = this.productRepository.findAll();
        return products.stream().map(ProductMapper::toDTO).toList();
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product not found with id " + id);
        }
        return ProductMapper.toDTO(product.get());
    }
}
