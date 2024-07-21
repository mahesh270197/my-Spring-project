package com.projects.banking.advices;

import lombok.Data;

import java.time.LocalTime;
@Data
public class ApiResponse<T> {

    private LocalTime localtime;

    private T data;

    private ErrorDetails error;

    public ApiResponse() {
        this.localtime = LocalTime.now();
    }
    public ApiResponse(T data) {
       this();
        this.data = data;
    }

    public ApiResponse(ErrorDetails error) {

        this.error = error;
    }


}
