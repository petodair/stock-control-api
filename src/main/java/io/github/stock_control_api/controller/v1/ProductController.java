package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.dto.v1.product.ProductRequestDTO;
import io.github.stock_control_api.dto.v1.product.ProductResponseDTO;
import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.mapper.v1.ProductMapper;
import io.github.stock_control_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        List<ProductResponseDTO> products = productService.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        ProductResponseDTO productDto = productMapper.toDto(productService.findById(id));
        return ResponseEntity.ok(productDto);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestBody ProductRequestDTO productRequestDTO){
        Product product = productMapper.toEntity(productRequestDTO);
        product = productService.save(product);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(uri).body(productMapper.toDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @RequestBody ProductRequestDTO productRequestDTO,
            @PathVariable Long id){
        Product product = productMapper.toEntity(productRequestDTO);
        product = productService.update(product,id);
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
