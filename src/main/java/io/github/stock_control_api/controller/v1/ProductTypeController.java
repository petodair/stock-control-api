package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.builder.ResponseBuilder;
import io.github.stock_control_api.dto.v1.ApiResponse;
import io.github.stock_control_api.dto.v1.producttype.ProductTypeRequestDTO;
import io.github.stock_control_api.dto.v1.producttype.ProductTypeResponseDTO;
import io.github.stock_control_api.entity.ProductType;
import io.github.stock_control_api.mapper.v1.ProductTypeMapper;
import io.github.stock_control_api.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/product-type")
public class ProductTypeController {

    private final ProductTypeService productTypeService;
    private final ProductTypeMapper productTypeMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductTypeResponseDTO>>> findAll() {
        List<ProductTypeResponseDTO> list = this.productTypeService
                .findAll().stream()
                .map(productTypeMapper::toDTO)
                .toList();
        return ResponseBuilder.<List<ProductTypeResponseDTO>>builder()
                .message("tipos de produtos retornados com sucesso")
                .data(list)
                .ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductTypeResponseDTO>> findById(@PathVariable Long id) {
        ProductType productType = this.productTypeService.findById(id);
        ProductTypeResponseDTO dto = productTypeMapper.toDTO(productType);
        return ResponseBuilder.<ProductTypeResponseDTO>builder()
                .message("tipo de produto encontrado e retornado com sucesso!")
                .data(dto)
                .ok();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductTypeResponseDTO>> save(
            @RequestBody ProductTypeRequestDTO dto
    ) {
        ProductType productCreated = this.productTypeService.save(productTypeMapper.toEntity(dto));
        return ResponseBuilder.<ProductTypeResponseDTO>builder()
                .message("tipo de produto cadastrado com sucesso")
                .data(productTypeMapper.toDTO(productCreated))
                .created(productCreated.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductTypeResponseDTO>> update(
            @RequestBody ProductTypeRequestDTO dto,
            @PathVariable Long id
    ){
        ProductType productUpdated = this.productTypeService.update(productTypeMapper.toEntity(dto), id);
        return ResponseBuilder.<ProductTypeResponseDTO>builder()
                .message("tipo de produto atualizado com sucesso")
                .data(productTypeMapper.toDTO(productUpdated))
                .ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.productTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
