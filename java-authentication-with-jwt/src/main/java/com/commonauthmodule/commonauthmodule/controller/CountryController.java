package com.commonauthmodule.commonauthmodule.controller;

import com.commonauthmodule.commonauthmodule.config.JwtUtil;
import com.commonauthmodule.commonauthmodule.entity.MyUserDetails;
import com.commonauthmodule.commonauthmodule.entity.User;
import com.commonauthmodule.commonauthmodule.model.Country;
import com.commonauthmodule.commonauthmodule.model.CountryDTO;
import com.commonauthmodule.commonauthmodule.service.CountryService;
import com.commonauthmodule.commonauthmodule.service.UserCurrencyService;
import com.commonauthmodule.commonauthmodule.service.UserDetailsServiceImpl;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;

@RestController
public class CountryController {

    private final Bucket bucket;
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserCurrencyService userCurrencyService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    public CountryController() {
        Bandwidth limit = Bandwidth.classic(30, Refill.greedy(30, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/country")
    public ResponseEntity<?> getCountryDetail(@RequestBody CountryDTO country, HttpServletRequest request) {
        Country countryDetail = countryService.getCountryDetail(country.getName());

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            try {
                String jwtToken = bearerToken.substring(7, bearerToken.length());
                User user = userDetailsService.getUserByName(jwtTokenUtil.getUsernameFromToken(jwtToken));
                if (countryDetail != null && user != null) {
                    userCurrencyService.saveUserCurrency(user, countryDetail);
                }

            } catch (Exception e) {

            }
        }
        return ResponseEntity.ok(countryDetail);
    }


}
