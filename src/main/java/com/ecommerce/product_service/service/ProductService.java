package com.ecommerce.product_service.service;

import com.ecommerce.product_service.model.dto.ProductRequest;
import com.ecommerce.product_service.model.dto.ProductResponse;
import com.ecommerce.product_service.model.dto.ProductUpdateRequest;
import com.ecommerce.product_service.model.response.DataResponse;
import com.ecommerce.product_service.model.response.DatatableResponse;
import com.ecommerce.product_service.model.response.DefaultResponse;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ProductService {
    DefaultResponse createProduct(ProductRequest productRequest);

    DatatableResponse<ProductResponse> getListProduct(int page, int limit, String sortField, String sortOrder);

    DataResponse<ProductResponse> getProductById(String id);

    DefaultResponse deleteProduct(String id);

    DefaultResponse updateProduct(String id, ProductUpdateRequest request) throws JsonMappingException;
}
