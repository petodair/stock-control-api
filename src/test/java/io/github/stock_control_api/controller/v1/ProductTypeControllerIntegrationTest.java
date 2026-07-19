package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.AbstractIntegrationTest;
import io.github.stock_control_api.entity.ProductType;
import io.github.stock_control_api.mock.ProductTypeMock;
import io.github.stock_control_api.repository.ProductRepository;
import io.github.stock_control_api.repository.ProductTypeRepository;
import io.github.stock_control_api.service.ProductService;
import io.github.stock_control_api.service.ProductTypeService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static io.restassured.RestAssured.given;

public class ProductTypeControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

    @Override
    protected JpaRepository<?, ?> repository() {
        return productTypeRepository;
    }

    @Test
    void shouldCreateProductType(){
        ProductType productType = ProductTypeMock.mockProductType();
        given()
                .body(productType)
                .contentType(ContentType.JSON)
        .when()
                .post("v1/product-types")
        .then()
                .log()
                .all()
                .statusCode(201);
    }
}
