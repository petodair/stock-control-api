package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.exception.product.ProductAlreadyExistsException;
import io.github.stock_control_api.exception.product.ProductNotFoundException;
import io.github.stock_control_api.repository.ProductRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductValidator implements Validator<Product>{

    private final ProductRepository productRepository;

    @Override
    public void shouldExists(Product product){
        if(!productRepository.existsById(product.getId())){
            throw new ProductNotFoundException("Produto não encontrado para a operação");
        }
    }

    @Override
    public void shouldNotExists(Product product){
        if(productRepository.existsByCode(product.getCode())){
            throw new ProductAlreadyExistsException("Já existe esse produto " +
                    "cadastrado como o código: " + product.getCode());
        }
    }

    @Override
    public void checkUpdate(Product newProduct, Product oldProduct){
        if(!oldProduct.getCode().equals(newProduct.getCode())){
            shouldNotExists(newProduct);
        }
        if(StringUtils.isNotBlank(newProduct.getName())) {
            oldProduct.setName(newProduct.getName());
        }
        if(StringUtils.isNotBlank(newProduct.getCode())){
            oldProduct.setCode(newProduct.getCode());
        }
        if(ObjectUtils.isNotEmpty(newProduct.getPrice())){
            oldProduct.setPrice(newProduct.getPrice());
        }
        if(ObjectUtils.isNotEmpty(newProduct.getProductType())){
            oldProduct.setProductType(newProduct.getProductType());
        }
    }
}
