package com.productservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DatatableResponse<T> {
    String result = "Success";
    String detail;
    String date;
    int code = 200;
    PageDataResponse<T> data;

    public DatatableResponse(String detail, PageDataResponse<T> data){
        this.detail = detail;
        this.date = String.valueOf(new Date());
        this.data = data;
    }
}
