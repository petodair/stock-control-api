package br.com.stock_control_api.controller;

import br.com.stock_control_api.builder.ResponseBuilder;
import br.com.stock_control_api.dto.ApiResponse;
import br.com.stock_control_api.dto.batch.BatchRequestDTO;
import br.com.stock_control_api.dto.batch.BatchResponseDTO;
import br.com.stock_control_api.enums.BatchLocal;
import br.com.stock_control_api.service.batch.BatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BatchResponseDTO>> save(@RequestBody @Valid BatchRequestDTO dto) {
        BatchResponseDTO batch = batchService.save(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(batch.id())
                .toUri();
        return ResponseBuilder.created(uri,"Lote adicionado com sucesso", batch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BatchResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody @Valid BatchRequestDTO dto) {
        return ResponseBuilder.success(HttpStatus.OK,"Lote atualizado com sucesso",
                batchService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BatchResponseDTO>>> findAll(
            @RequestParam(name = "batchNumber", required = false) String batchNumber,
            @RequestParam(name = "batchLocal", required = false) BatchLocal batchLocal
            ) {
        return ResponseBuilder.success(HttpStatus.OK,"Lotes retornados com sucesso",
                this.batchService.findAll(batchNumber, batchLocal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BatchResponseDTO>> findById(@PathVariable Long id){
        return ResponseBuilder.success(HttpStatus.OK,"Lote retornado com sucesso!",
                batchService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.batchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
