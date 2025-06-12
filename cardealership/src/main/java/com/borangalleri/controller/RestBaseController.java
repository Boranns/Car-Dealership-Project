package com.borangalleri.controller;

public class RestBaseController {
    
    public<T> RootEntity<T> ok(T payLoad){
        return RootEntity.ok(payLoad);
    }

    public<T> RootEntity<T> error(String errorMessage){
        return RootEntity.error(errorMessage);
    }

}
