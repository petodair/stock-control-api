package br.com.stock_control_api.controller;

import br.com.stock_control_api.builder.ResponseBuilder;
import br.com.stock_control_api.dto.ApiResponse;
import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.dto.product.ProductResponseDTO;
import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.enums.MeatType;
import br.com.stock_control_api.enums.StorageType;
import br.com.stock_control_api.service.product.ProductService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<ProductResponseDTO>> save(@RequestBody ProductRequestDTO dto){
        ProductResponseDTO product = this.productService.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri();

        return ResponseBuilder.created(uri, "Produto Criado com Sucesso!", product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> update(@PathVariable Long id,
                                                       @RequestBody ProductRequestDTO dto){
        return ResponseBuilder.success(HttpStatus.OK,
                "Produto atualizado com sucesso",
                this.productService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> findAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "meatType", required = false) MeatType meatType,
            @RequestParam(value = "storageType", required = false) StorageType storageType
            ){
        return ResponseBuilder.success(HttpStatus.OK, "Produtos retornados com Sucesso!",
                this.productService.findAll(name,code,meatType,storageType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> findById(@PathVariable Long id){
        return ResponseBuilder.success(HttpStatus.OK, "Produto encontrado e retornado com sucesso!",
                this.productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id){
        this.productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
