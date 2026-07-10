package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.AbstractIntegrationTest;
import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.mock.ProductMock;
import io.github.stock_control_api.repository.ProductRepository;
import io.github.stock_control_api.service.ProductService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import static io.restassured.RestAssured.*;

public class ProductControllerIntegrationTest extends AbstractIntegrationTest{

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

    @Override
    protected JpaRepository<?, ?> repository() {
        return productRepository;
    }

    @Test
    void shouldFindProductById() {
        Product product = this.productService.save(ProductMock.mockProduct());

        given()
            .pathParam("id", product.getId())

        .when()
            .get("/api/v1/products/{id}")

        .then()
            .statusCode(200);
    }

    @Test
    void shouldCreateProduct(){
        Product product = ProductMock.mockProduct();

        given()
                .contentType(ContentType.JSON)
                .body(product)

        .when()
                .post("api/v1/products")

        .then()
                .log()
                .all()
                .statusCode(201);
    }

    @Test
    void shouldDeleteProduct(){
        Product product = this.productService.save(ProductMock.mockProduct());

        given()
                .pathParam("id", product.getId())

        .when()
                .delete("/api/v1/products/{id}")

        .then()
                .statusCode(204);
    }
}
