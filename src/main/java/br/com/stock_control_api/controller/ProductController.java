package br.com.stock_control_api.controller;

import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.dto.product.ProductResponseDTO;
import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductRequestDTO dto){
        Product product = this.productService.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        return ResponseEntity.ok(this.productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.productService.findById(id));
    }
}
