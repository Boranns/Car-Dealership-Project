package com.borangalleri.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RootEntity<T> {
    private Integer status;
    private T payLoad;
    private String errorMessage;

    public static <T> RootEntity<T> ok(T payLoad){
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setStatus(200);
        rootEntity.setErrorMessage(null);
        rootEntity.setPayLoad(payLoad);

        return rootEntity;
    }

    public static <T> RootEntity<T> error(String errorMessage){
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setStatus(500);
        rootEntity.setPayLoad(null);
        rootEntity.setErrorMessage(errorMessage);

        return rootEntity;
    }
}
