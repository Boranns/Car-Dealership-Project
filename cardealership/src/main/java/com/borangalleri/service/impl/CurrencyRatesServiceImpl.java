package com.borangalleri.service.impl;

import com.borangalleri.dto.CurrencyRatesResponse;
import com.borangalleri.exception.BaseException;
import com.borangalleri.exception.ErrorMessage;
import com.borangalleri.exception.MessageType;
import com.borangalleri.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String rootUrl = "https://evds2.tcbm.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";
        String endpoint = rootUrl + "series=" + series + "&StartDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "XsBxAxzaVo");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {}
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_ARE_OCCURED, e.getMessage()));
        }

        return null;
    }
}
