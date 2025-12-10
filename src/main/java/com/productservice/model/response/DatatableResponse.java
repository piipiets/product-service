package com.productservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.productservice.model.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatatableResponse<T> {
    String result;
    String detail;
    Date date;
    int code;
    PageDataResponse<ProductResponse> data;
}
