package br.com.stock_control_api.service.product;

import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.dto.product.ProductResponseDTO;
import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.enums.MeatType;
import br.com.stock_control_api.enums.StorageType;
import br.com.stock_control_api.mapper.ProductMapper;
import br.com.stock_control_api.repository.ProductRepository;
import br.com.stock_control_api.validator.product.ProductValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    public ProductServiceImpl(ProductRepository productRepository,  ProductValidator productValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    @Override
    public Product save(ProductRequestDTO dto) {
        productValidator.validate(dto);
        return this.productRepository.save(ProductMapper.toEntity(dto));
    }

    public Product update(Long id, ProductRequestDTO dto) {

        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto com o id " + id + " não " +
                        "encontrado para atualização"));

        if(!dto.code().equals(product.getCode())){
            productValidator.validate(dto);
        }

        return this.productRepository.save(ProductMapper.toEntity(id, dto));
    }

    @Override
    public List<ProductResponseDTO> findAll(String name,
                                            String code,
                                            MeatType meatType,
                                            StorageType storageType) {
        List<Product> products = this.productRepository.findWithFilters(name, code, meatType, storageType);
        return products.stream().map(ProductMapper::toDTO).toList();
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado com o id: " + id);
        }
        return ProductMapper.toDTO(product.get());
    }

    @Override
    public void deleteById(Long id) {
        if(!this.productRepository.existsById(id)){
            throw new EntityNotFoundException("Produto com o id: " + id + " não encontrado para exclusão");
        }
        this.productRepository.deleteById(id);
    }
}
