package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.builder.ResponseBuilder;
import io.github.stock_control_api.dto.v1.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> findAll(){
        List<ProductResponseDTO> products = productService.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
        return new ResponseBuilder<List<ProductResponseDTO>>()
                .message("Produtos retornados com sucesso!")
                .data(products)
                .ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> findById(@PathVariable Long id){
        ProductResponseDTO productDto = productMapper.toDto(productService.findById(id));
        return new ResponseBuilder<ProductResponseDTO>()
                .message("Produto de id "+id+" retornado com sucesso")
                .data(productDto)
                .ok();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> save(@RequestBody ProductRequestDTO productRequestDTO){
        Product product = productMapper.toEntity(productRequestDTO);
        product = productService.save(product);

        return new ResponseBuilder<ProductResponseDTO>()
                .data(productMapper.toDto(product))
                .message("Produto criado com sucesso")
                .created(product.getId());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> update(
            @RequestBody ProductRequestDTO productRequestDTO,
            @PathVariable Long id){
        Product product = productMapper.toEntity(productRequestDTO);
        product = productService.update(product,id);

        return new ResponseBuilder<ProductResponseDTO>()
                .message("Produto atualizado com sucesso")
                .data(productMapper.toDto(product))
                .ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
