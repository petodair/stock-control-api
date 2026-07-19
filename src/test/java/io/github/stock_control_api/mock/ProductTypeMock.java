package io.github.stock_control_api.mock;

import io.github.stock_control_api.entity.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeMock {
    private ProductTypeMock(){}

    public static ProductType mockProductType(){
        return new ProductType("Perecível");
    }

    public static List<ProductType> mockListWith10ProductTypes(){
        List<ProductType> productTypes = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            productTypes.add(new ProductType("Type " + (i + 1)));
        }
        return productTypes;
    }
}
