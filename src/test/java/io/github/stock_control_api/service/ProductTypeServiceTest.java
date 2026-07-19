package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.ProductType;
import io.github.stock_control_api.exception.producttype.ProductTypeNotFoundException;
import io.github.stock_control_api.mock.ProductTypeMock;
import io.github.stock_control_api.repository.ProductTypeRepository;
import io.github.stock_control_api.validate.ProductTypeValidate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTypeServiceTest {

    @Mock
    ProductTypeRepository productTypeRepository;

    @Mock
    ProductTypeValidate  productTypeValidate;

    @InjectMocks
    ProductTypeService productTypeService;


    @Test
    void shouldCreateProductType(){
        ProductType productType = ProductTypeMock.mockProductType();

        when(
                this.productTypeRepository.save(any(ProductType.class))
        ).thenAnswer(invocation -> {
            productType.setId(1L);
            return productType;
        });

        ProductType productTypeCreated = this.productTypeService.save(productType);
        Assertions.assertNotNull(productTypeCreated);
        Assertions.assertNotNull(productTypeCreated.getId());
    }

    @Test
    void shouldReturnListWith10ProductTypes(){
        when(
                productTypeRepository.findAll()
        ).thenReturn(ProductTypeMock.mockListWith10ProductTypes());

        List<ProductType> productTypes = this.productTypeService.findAll();

        Assertions.assertNotNull(productTypes);
        Assertions.assertEquals(10, productTypes.size());
    }

    @Test
    void shouldReturnProductTypeById(){
        when(
                productTypeRepository.findById(anyLong())
        ).thenAnswer(invocation -> {
            ProductType productTypeReturn = new ProductType();
            productTypeReturn.setId(1L);
            return Optional.of(productTypeReturn);
        });

        ProductType productTypeFound = this.productTypeService.findById(1L);
        Assertions.assertNotNull(productTypeFound);
        Assertions.assertNotNull(productTypeFound.getId());
    }

    @Test
    void shouldThrowExceptionWhenProductTypeNotFound(){
        when(
                productTypeRepository.findById(anyLong())
        ).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductTypeNotFoundException.class,
                () -> this.productTypeService.findById(1L));
    }
}
