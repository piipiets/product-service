package com.productservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.productservice.model.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDataResponse<T> {
    int page;
    int limit;
    Integer total;
    List<ProductResponse> list;
}
