package io.github.stock_control_api.service;

import io.github.stock_control_api.dto.v1.product.ProductFilter;
import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.exception.product.ProductNotFoundException;
import io.github.stock_control_api.repository.ProductRepository;
import io.github.stock_control_api.specification.ProductSpecification;
import io.github.stock_control_api.validate.ProductTypeValidator;
import io.github.stock_control_api.validate.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductTypeValidator typeValidator;
    private final ProductValidator validator;

    public List<Product> findAll(ProductFilter productFilter) {
        return repository.findAll(ProductSpecification.withFilter(productFilter));
    }

    public Product findById(Long id){
        return repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(id));
    }

    @Transactional
    public Product save(Product product){
        this.validator.shouldNotExists(product);
        this.typeValidator.shouldExists(product.getProductType());
        return repository.save(product);
    }

    @Transactional
    public Product update(Product product, Long id){
        Product productToUpdate = this.findById(id);
        this.typeValidator.shouldExists(product.getProductType());
        this.validator.checkUpdate(product, productToUpdate);
        return repository.save(productToUpdate);
    }

    @Transactional
    public void deleteById(Long id){
        findById(id);
        this.repository.deleteById(id);
    }
}
