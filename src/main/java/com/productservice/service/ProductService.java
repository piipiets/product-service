package com.productservice.service;

import com.productservice.model.dto.ProductRequest;
import com.productservice.model.dto.ProductResponse;
import com.productservice.model.dto.ProductUpdateRequest;
import com.productservice.model.response.DataResponse;
import com.productservice.model.response.DatatableResponse;
import com.productservice.model.response.DefaultResponse;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ProductService {
    DefaultResponse createProduct(ProductRequest productRequest);
    DatatableResponse<ProductResponse> getListProduct(int page, int limit, String sortField, String sortOrder);
    DataResponse<ProductResponse> getProductById(String id);
    DefaultResponse deleteProduct(String id);
    DefaultResponse updateProduct(String id, ProductUpdateRequest request) throws JsonMappingException;
}
