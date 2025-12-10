package com.productservice.service.impl;

import com.productservice.exception.custom.NotFoundException;
import com.productservice.model.dto.ProductRequest;
import com.productservice.model.dto.ProductResponse;
import com.productservice.model.dto.ProductUpdateRequest;
import com.productservice.model.entity.Product;
import com.productservice.model.response.*;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;
import com.productservice.utils.ObjectMapperUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public DefaultResponse createProduct(ProductRequest productRequest){
        try{
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());

            productRepository.save(product);

            return new DefaultResponse(ResponseMessage.DATA_CREATED, 200);
        } catch (Exception e){
            log.error("Error when creating product", e);
            throw e;
        }
    }

    @Override
    public DatatableResponse<ProductResponse> getListProduct(int page, int limit, String sortField, String sortOrder){
        try{
            String[] allowedOrder = {"id", "name", "price"};

            Sort sort = Sort.by(Sort.Direction.ASC, "id");
            if (Arrays.asList(allowedOrder).contains(sortField)) {
                sort = sortOrder.equalsIgnoreCase("ASC") ? Sort.by(Sort.Direction.ASC, sortField) : Sort.by(Sort.Direction.DESC, sortField);
            }

            Pageable pageable = PageRequest.of(page - 1, limit, sort);
            Page<Product> listProducts = productRepository.findAll(pageable);
            PageDataResponse<ProductResponse> response = new PageDataResponse<>();
            response.setPage(page);
            response.setLimit(limit);
            response.setTotal((int) listProducts.getTotalElements());
            response.setList(listProducts.getContent().stream().map(this::mapToProductResponse).collect(Collectors.toList()));

            return new DatatableResponse<>("Success", ResponseMessage.DATA_FETCHED, new Date(), 200, response);
        } catch (Exception e){
            log.error("Error when get list of products", e);
            throw e;
        }
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public DataResponse<ProductResponse> getProductById(String id) {
        try{
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.DATA_NOT_FOUND));
            ProductResponse res = mapToProductResponse(product);
            return new DataResponse<>("Success", ResponseMessage.DATA_FETCHED, new Date(), 200, res);
        } catch(Exception e){
            log.error("Error when get product's data", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse deleteProduct(String id) {
        try{
            if (!productRepository.existsById(id)) {
                throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
            }

            productRepository.deleteById(id);
            return new DefaultResponse(ResponseMessage.DATA_DELETED, 200);
        } catch(Exception e){
            log.error("Error when delete product", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse updateProduct(String id, ProductUpdateRequest request) throws JsonMappingException {
        try{
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.DATA_NOT_FOUND));
            ObjectMapperUtil.updateObject(request, product);
            productRepository.save(product);
            return new DefaultResponse(ResponseMessage.DATA_UPDATED, 200);
        } catch(Exception e){
            log.error("Error when updateing product", e);
            throw e;
        }
    }
}
