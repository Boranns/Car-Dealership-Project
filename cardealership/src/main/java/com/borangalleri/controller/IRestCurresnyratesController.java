package com.borangalleri.controller;

import com.borangalleri.dto.CurrencyRatesResponse;

public interface IRestCurresnyratesController {
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);

}
