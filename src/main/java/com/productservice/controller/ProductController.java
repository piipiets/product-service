package com.productservice.controller;

import com.productservice.model.dto.ProductRequest;
import com.productservice.model.dto.ProductResponse;
import com.productservice.model.dto.ProductUpdateRequest;
import com.productservice.model.response.DataResponse;
import com.productservice.model.response.DatatableResponse;
import com.productservice.model.response.DefaultResponse;
import com.productservice.service.ProductService;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/create")
    public ResponseEntity<DefaultResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        DefaultResponse res = productService.createProduct(productRequest);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<DatatableResponse> getListProduct(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int limit,
            @RequestParam(defaultValue = "id", required = false) String sortField,
            @RequestParam(defaultValue = "asc", required = false) String sortOrder
    ){
        DatatableResponse res = productService.getListProduct(page, limit, sortField, sortOrder);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<DataResponse> getProductById(@RequestParam String id) {
        DataResponse res = productService.getProductById(id);
        return ResponseEntity.ok().body(res);
    }

    @PatchMapping(path = "/edit")
    public ResponseEntity<DefaultResponse> updateProduct(
            @RequestParam String id,
            @RequestBody ProductUpdateRequest productRequest) throws JsonMappingException {
        DefaultResponse res = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<DefaultResponse> deleteProduct(@RequestParam String id) {
        DefaultResponse res = productService.deleteProduct(id);
        return ResponseEntity.ok().body(res);
    }
}
