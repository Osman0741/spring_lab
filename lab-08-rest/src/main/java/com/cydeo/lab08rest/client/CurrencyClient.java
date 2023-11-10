package com.cydeo.lab08rest.client;

import com.cydeo.lab08rest.dto.CurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="https://apilayer.net/api", name ="CURRENCY-CLIENT")
public interface CurrencyClient {

    @GetMapping("/live")
    CurrencyDTO getCurrency(@RequestParam(value = "access_key") String key,
                         @RequestParam(value = "currency") String currency,
                         @RequestParam(value ="source") String source,
                         @RequestParam(value = "format") Integer format);

}
