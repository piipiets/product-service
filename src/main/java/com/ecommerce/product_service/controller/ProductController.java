package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.model.dto.ProductRequest;
import com.ecommerce.product_service.model.dto.ProductResponse;
import com.ecommerce.product_service.model.dto.ProductUpdateRequest;
import com.ecommerce.product_service.model.response.DataResponse;
import com.ecommerce.product_service.model.response.DatatableResponse;
import com.ecommerce.product_service.model.response.DefaultResponse;
import com.ecommerce.product_service.service.ProductService;
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
    public ResponseEntity<DatatableResponse<ProductResponse>> getListProduct(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int limit,
            @RequestParam(defaultValue = "id", required = false) String sortField,
            @RequestParam(defaultValue = "asc", required = false) String sortOrder
    ){
        DatatableResponse<ProductResponse> res = productService.getListProduct(page, limit, sortField, sortOrder);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<DataResponse<ProductResponse>> getProductById(@RequestParam String id) {
        DataResponse<ProductResponse> res = productService.getProductById(id);
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
