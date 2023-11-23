package com.cydeo.lab08rest.client;

import com.cydeo.lab08rest.dto.CurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(url="http://api.currencylayer.com", name ="CURRENCY-CLIENT")
public interface CurrencyClient {

    @GetMapping("/live")
    ResponseEntity<CurrencyDTO> getCurrency(@RequestParam(value = "access_key") String access_key,
                                           @RequestParam(value = "currencies") String currencies);

}
