package com.borangalleri.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004", "Record Not Found."),
    TOKEN_IS_EXPIRED("1005", "Token Has Expired."),
    USERNAME_NOT_FOUND("1006", "Username Not Found."),
    INVALID_USERNAME_OR_PASSWORD("1007", "Invalid Username Or Password."),
    REFRESH_TOKEN_NOT_FOUND("1008", "Refresh Token Not Found."),
    REFRESH_TOKEN_IS_EXPIRED("1009", "Refresh Token Has Expired."),
    CURRENCY_RATES_ARE_OCCURED("1010", "Currency Rates Update Failed."),
    CUSTOMER_AMOUNT_NOT_ENOUGH("1011", "Insufficient Customer Funds."),
    CAR_ALREADY_SOLD("1012", "The Car Has Already Been Sold."),
    GENERAL_EXCEPTION("9999", "An Unexpected Error Occurred.");

    private String code;

    private String message;

     MessageType(String code, String message){
        this.code = code;
        this.message = message;
    }

}
