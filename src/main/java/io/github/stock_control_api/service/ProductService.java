package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.exception.product.ProductNotFoundException;
import io.github.stock_control_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(id));
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product update(Product product, Long id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado para ser atualizado");
        }
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado para ser excluído");
        }
        productRepository.deleteById(id);
    }
}
