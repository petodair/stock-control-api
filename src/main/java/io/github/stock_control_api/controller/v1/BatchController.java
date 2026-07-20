package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.builder.ResponseBuilder;
import io.github.stock_control_api.dto.v1.ApiResponse;
import io.github.stock_control_api.dto.v1.batch.BatchFilter;
import io.github.stock_control_api.dto.v1.batch.BatchRequestDTO;
import io.github.stock_control_api.dto.v1.batch.BatchResponseDTO;
import io.github.stock_control_api.entity.Batch;
import io.github.stock_control_api.mapper.v1.BatchMapper;
import io.github.stock_control_api.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;
    private final BatchMapper batchMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BatchResponseDTO>>> findAll(
            BatchFilter batchFilter
    ){
        List<Batch> batches = this.batchService.findAll(batchFilter);
        return ResponseBuilder.<List<BatchResponseDTO>>builder()
                .message("lotes retornados com sucesso")
                .data(batches
                                .stream()
                                .map(batchMapper::toDto).
                                toList()
                )
                .ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BatchResponseDTO>> findById(
            @PathVariable Long id
    ){
        BatchResponseDTO dto = batchMapper.toDto(this.batchService.findById(id));
        return ResponseBuilder.<BatchResponseDTO>builder()
                .message("lote encontrado e retornado com sucesso")
                .data(dto)
                .ok();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BatchResponseDTO>> save(
            @RequestBody BatchRequestDTO dto
    ){
        Batch batchSaved = this.batchService.save(batchMapper.toEntity(dto));
        return ResponseBuilder.<BatchResponseDTO>builder()
                .message("lote criado com sucesso")
                .data(this.batchMapper.toDto(batchSaved))
                .created(batchSaved.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BatchResponseDTO>> update(
            @RequestBody BatchRequestDTO dto,
            @PathVariable Long id
    ){
        Batch batchUpdated = this.batchService.update(batchMapper.toEntity(dto), id);
        return ResponseBuilder.<BatchResponseDTO>builder()
                .message("produto atualizado com sucesso")
                .data(this.batchMapper.toDto(batchUpdated))
                .ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
           @PathVariable Long id
    ){
        this.batchService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
