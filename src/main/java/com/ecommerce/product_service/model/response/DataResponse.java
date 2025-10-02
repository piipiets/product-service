package com.ecommerce.product_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DataResponse<T> {
    String result = "Success";
    String detail;
    Date date;
    int code = 200;
    T data;

    public DataResponse(String detail, T data){
        this.detail = detail;
        this.date = new Date();
        this.data = data;
    }
}