package com.commonauthmodule.commonauthmodule.service;

import com.commonauthmodule.commonauthmodule.model.Country;
import com.commonauthmodule.commonauthmodule.model.Currency;
import com.commonauthmodule.commonauthmodule.model.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryService {

    private final WebClient.Builder webClient;

    public Country getCountryDetail(String name) {

        String uri = "https://restcountries.com/v2/name/" + name + "?fields=name,population,currencies";

        Country[] response = webClient.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Country[].class).block();

        Country country = response[0];

        for (Currency currency : country.getCurrencies()) {
            ExchangeRate exchangeRate = webClient.build()
                    .get()
                    .uri("https://api.frankfurter.app/latest?amount=1&from=" + currency.getCode() + "&to=INR")
                    .retrieve()
                    .bodyToMono(ExchangeRate.class).block();

            double rate = exchangeRate.getRates().getInr();
            currency.setExchangeRate(rate);
        }
        return country;
    }
}
