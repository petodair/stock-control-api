package io.github.stock_control_api.builder;

import io.github.stock_control_api.dto.v1.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ResponseBuilder<D> {

    ApiResponse.ResponseStatus responseStatus = ApiResponse.ResponseStatus.SUCCESS;
    HttpStatus status = HttpStatus.OK;
    private String message = "";
    private D data;

    private ResponseBuilder() {}

    public static <D> ResponseBuilder<D> builder() {
        return new ResponseBuilder<>();
    }

    public ResponseEntity<ApiResponse<D>> build(){
        return ResponseEntity.status(this.status).body(createResponse());
    }

    public ResponseEntity<ApiResponse<D>> ok(){
        this.status = HttpStatus.OK;
        this.responseStatus = ApiResponse.ResponseStatus.SUCCESS;
        return build();
    }

    public <ID> ResponseEntity<ApiResponse<D>> created(ID id){
        this.status = HttpStatus.CREATED;
        this.responseStatus = ApiResponse.ResponseStatus.SUCCESS;
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).body(createResponse());
    }

    public ResponseEntity<ApiResponse<D>> notFound(){
        this.status = HttpStatus.NOT_FOUND;
        this.responseStatus = ApiResponse.ResponseStatus.ERROR;
        this.data = null;
        return build();
    }

    public ApiResponse<D> createResponse(){
        return new ApiResponse<>(
                this.responseStatus,
                this.status,
                this.message,
                this.data
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
