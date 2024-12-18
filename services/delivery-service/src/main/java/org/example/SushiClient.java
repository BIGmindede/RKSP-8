package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sushiClient", url = "${sushi.service.url}")
public interface SushiClient {
    @GetMapping("/api/sushi/{id}")
    Sushi getSushiById(@PathVariable Long id);
}
