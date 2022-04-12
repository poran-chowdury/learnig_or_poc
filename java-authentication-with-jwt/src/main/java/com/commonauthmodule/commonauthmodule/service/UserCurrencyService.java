package com.commonauthmodule.commonauthmodule.service;

import com.commonauthmodule.commonauthmodule.entity.User;
import com.commonauthmodule.commonauthmodule.entity.UserCurrency;
import com.commonauthmodule.commonauthmodule.model.Country;
import com.commonauthmodule.commonauthmodule.model.Currency;
import com.commonauthmodule.commonauthmodule.repository.UserCurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCurrencyService {
    Logger logger = LoggerFactory.getLogger(UserCurrency.class);
    @Autowired
    private UserCurrencyRepository repo;

    public UserCurrencyService() {
    }

    public void saveUserCurrency(User user, Country country) {
        List<UserCurrency> userCurrencies = new ArrayList<>();
        for (Currency currency : country.getCurrencies()) {
            UserCurrency userCurrency = new UserCurrency();
            userCurrency.setUser(user);
            userCurrency.setCode(currency.getCode());
            userCurrency.setExchangeRate(currency.getExchangeRate());
            userCurrencies.add(userCurrency);
        }
        try {
            repo.saveAll(userCurrencies);
            logger.info("save successfully");
        } catch (Exception e) {
            logger.error("problem in save user currency");
        }
    }
}
