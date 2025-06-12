package com.borangalleri.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.borangalleri.controller.IRestCurresnyratesController;
import com.borangalleri.controller.RestBaseController;
import com.borangalleri.controller.RootEntity;
import com.borangalleri.dto.CurrencyRatesResponse;
import com.borangalleri.service.ICurrencyRatesService;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurresnyratesController{

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @GetMapping("/currency-rates")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(
        @RequestParam("startDate") String startDate, 
        @RequestParam("endDate") String endDate) {
        
        return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
    }

}
