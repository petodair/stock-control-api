package io.github.stock_control_api.service;

import io.github.stock_control_api.dto.v1.product.ProductFilter;
import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.exception.product.ProductNotFoundException;
import io.github.stock_control_api.repository.ProductRepository;
import io.github.stock_control_api.specification.ProductSpecification;
import io.github.stock_control_api.validate.ProductTypeValidate;
import io.github.stock_control_api.validate.ProductValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeValidate productTypeValidate;
    private final ProductValidate productValidate;

    public List<Product> findAll(ProductFilter productFilter) {
        return productRepository.findAll(ProductSpecification.withFilter(productFilter));
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(id));
    }

    @Transactional
    public Product save(Product product){
        this.productValidate.existsByCode(product.getCode());
        this.productTypeValidate.existsById(product.getProductType().getId());
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product product, Long id){
        Product productToUpdate = this.findById(id);
        this.productTypeValidate.existsById(product.getProductType().getId());
        this.productValidate.toUpdate(product, productToUpdate);
        return productRepository.save(productToUpdate);
    }

    @Transactional
    public void deleteById(Long id){
        this.productValidate.existsById(id);
        this.productRepository.deleteById(id);
    }
}
