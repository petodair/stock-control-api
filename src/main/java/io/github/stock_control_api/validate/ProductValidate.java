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
public class ProductValidate {

    private final ProductRepository productRepository;

    public void existsById(Long id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado para a operação");
        }
    }

    public void existsByCode(String code){
        if(productRepository.existsByCode(code)){
            throw new ProductAlreadyExistsException("Já existe esse produto " +
                    "cadastrado como o código: " + code);
        }
    }

    public void toUpdate(Product newProduct, Product toUpdate){
        if(StringUtils.isNotBlank(newProduct.getName())) {
            toUpdate.setName(newProduct.getName());
        }
        if(StringUtils.isNotBlank(newProduct.getCode())){
            toUpdate.setCode(newProduct.getCode());
        }
        if(ObjectUtils.isNotEmpty(newProduct.getPrice())){
            toUpdate.setPrice(newProduct.getPrice());
        }
        if(ObjectUtils.isNotEmpty(newProduct.getProductType())){
            toUpdate.setProductType(newProduct.getProductType());
        }
    }
}
