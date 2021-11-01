package com.xuan.diary.common.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<E> {

    private ResponseCode status;

    private String message;

    private E item;


    public Response(E item, ResponseCode code, String message) {
        this.status = code;
        this.message = message;
        this.item = item;
    }

    public Response(ResponseCode code, String message) {
        this.status = code;
        this.message = message;
    }
}