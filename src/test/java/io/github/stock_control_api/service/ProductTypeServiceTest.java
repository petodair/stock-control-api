package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.ProductType;
import io.github.stock_control_api.exception.producttype.ProductTypeNotFoundException;
import io.github.stock_control_api.mock.ProductTypeMock;
import io.github.stock_control_api.repository.ProductTypeRepository;
import io.restassured.internal.common.assertion.Assertion;
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

    @InjectMocks
    ProductTypeService productTypeService;

    @Test
    void shouldCreateProductType(){
        ProductType productType = ProductTypeMock.mockProductType();

        when(
                this.productTypeRepository.save(any(ProductType.class))
        ).thenReturn(productType);

        productType = this.productTypeService.save(productType);
        Assertions.assertNotNull(productType);
        Assertions.assertNotNull(productType.getId());
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
        ProductType productType = ProductTypeMock.mockProductType();
        when(
                productTypeRepository.findById(anyLong())
        ).thenReturn(Optional.of(productType));

        this.productTypeService.findById(productType.getId());
        Assertions.assertNotNull(productType);
        Assertions.assertNotNull(productType.getId());
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
