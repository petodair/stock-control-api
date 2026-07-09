package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.mock.ProductMock;
import io.github.stock_control_api.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    void shouldFindProductById() {
        when(
                this.productRepository.findById(1L)
        ).thenReturn(Optional.of(ProductMock.mockProduct()));

        Product product = this.productService.findById(1L);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(1L, product.getId());
    }

    @Test
    void shouldCreateProduct(){
        Product product = ProductMock.mockProduct();
        when(
                this.productRepository.save(product)
        ).thenReturn(product);

        Product createdProduct = this.productService.save(product);

        Assertions.assertNotNull(createdProduct);
        Assertions.assertNotNull(createdProduct.getId());
        Assertions.assertEquals(product.getId(), createdProduct.getId());
    }

    @Test
    void shouldUpdateProduct(){
        Product product = ProductMock.mockProduct();
        String newName = "Nome diferente";
        product.setName(newName);

        when(this.productRepository.existsById(product.getId())).thenReturn(true);
        when(
                this.productRepository.save(product)
        ).thenReturn(product);

        Product updatedProduct = this.productService.update(product,1L);

        Assertions.assertNotNull(updatedProduct);
        Assertions.assertNotNull(updatedProduct.getId());
        Assertions.assertEquals(newName, updatedProduct.getName());
    }
}
