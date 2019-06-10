package com.kmvpsolutions.boutique.customerservice.client;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.CartDTO;
import com.kmvpsolutions.boutique.boutiquecommons.dtos.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-service")
public interface OrderServiceClient {

    @RequestMapping(value = "/api/orders", method = RequestMethod.POST)
    OrderDTO create(@RequestBody CartDTO cartDTO);
}
