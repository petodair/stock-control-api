package io.github.stock_control_api.builder;

import io.github.stock_control_api.dto.v1.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ResponseBuilder<D> {

    ApiResponse.ResponseStatus responseStatus = ApiResponse.ResponseStatus.SUCCESS;
    HttpStatus status = HttpStatus.CONTINUE;
    private String message = "";
    private D data;

    public ResponseBuilder() {}

    public ResponseEntity<ApiResponse<D>> ok(){
        return ResponseEntity.ok(
                new ApiResponse<>(
                        ApiResponse.ResponseStatus.SUCCESS,
                        HttpStatus.OK,
                        this.message,
                        data
                )
        );
    }

    public <ID> ResponseEntity<ApiResponse<D>> created(ID id){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).body(
                new ApiResponse<>(
                        ApiResponse.ResponseStatus.SUCCESS,
                        HttpStatus.CREATED,
                        this.message,
                        this.data
                )
        );
    }

    public ResponseEntity<ApiResponse<D>> build(){
        return ResponseEntity.status(this.status).body(
                new ApiResponse<>(
                        this.responseStatus,
                        this.status,
                        this.message,
                        this.data
                )
        );
    }

    public ResponseBuilder<D> responseStatus(ApiResponse.ResponseStatus responseStatus){
        this.responseStatus = responseStatus;
        return this;
    }

    public ResponseBuilder<D> status(HttpStatus status){
        this.status = status;
        return this;
    }

    public ResponseBuilder<D> message(String message){
        this.message = message;
        return this;
    }

    public ResponseBuilder<D> data(D data){
        this.data = data;
        return this;
    }
}
