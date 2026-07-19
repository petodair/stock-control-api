package io.github.stock_control_api.mock;

import io.github.stock_control_api.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductMock {

    private ProductMock(){}

    public static Product mockProduct(){
        return new Product("Alcatra c/ Maminha", "8679", new BigDecimal("39.90"));
    }

    public static Product mockProductWithId(){
        return new Product("Alcatra c/ Maminha", "8679", new BigDecimal("39.90"));
    }

    public static List<Product> mockProductList(){
        return List.of(
                mockProduct(),
                new Product("Miolo da Alcatra", "2592", new BigDecimal("39.90")),
                new Product("Frango Cong", "35385", new BigDecimal("9.98"))
        );
    }
}
