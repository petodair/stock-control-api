package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.builder.ResponseBuilder;
import io.github.stock_control_api.dto.v1.ApiResponse;
import io.github.stock_control_api.dto.v1.enterprise.EnterpriseRequestDTO;
import io.github.stock_control_api.dto.v1.enterprise.EnterpriseResponseDTO;
import io.github.stock_control_api.entity.Enterprise;
import io.github.stock_control_api.mapper.v1.EnterpriseMapper;
import io.github.stock_control_api.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/enterprises")
@RequiredArgsConstructor
public class EnterpriseController {

    private final EnterpriseService service;
    private final EnterpriseMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EnterpriseResponseDTO>> findById(
            @PathVariable Long id
    ){
        EnterpriseResponseDTO response = mapper.toDto(service.findById(id));
        return ResponseBuilder.<EnterpriseResponseDTO>builder()
                .message("Empresa encontrada com sucesso")
                .data(response)
                .ok();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EnterpriseResponseDTO>>> findAll(){
        List<Enterprise> list = this.service.findAll();
        return ResponseBuilder.<List<EnterpriseResponseDTO>>builder()
                .message("Empresas retornadas com sucesso")
                .data(list
                        .stream()
                        .map(mapper::toDto)
                        .toList())
                .ok();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EnterpriseResponseDTO>> save(
            @RequestBody EnterpriseRequestDTO dto
            ){
        Enterprise enterprise = this.service.save(mapper.toEntity(dto));
        EnterpriseResponseDTO response = mapper.toDto(enterprise);
        return ResponseBuilder.<EnterpriseResponseDTO>builder()
                .message("Empresa salva com sucesso!")
                .data(response)
                .created(response.id());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EnterpriseResponseDTO>> update(
            @RequestBody EnterpriseRequestDTO dto,
            @PathVariable Long id
    ){
        Enterprise enterprise = this.service.update(mapper.toEntity(dto),id);
        return ResponseBuilder.<EnterpriseResponseDTO>builder()
                .message("Empresa atualizada com sucesso")
                .data(mapper.toDto(enterprise))
                .ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id
    ){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
