package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.mock.ProductMock;
import io.github.stock_control_api.service.ProductService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static io.restassured.RestAssured.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private ProductService productService;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
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
