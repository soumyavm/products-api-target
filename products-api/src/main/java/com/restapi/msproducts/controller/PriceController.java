package com.restapi.msproducts.controller;

import com.restapi.msproducts.exception.ProductNotFoundException;
import com.restapi.msproducts.model.Price;
import com.restapi.msproducts.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/rest/v1")
public class PriceController {

    @Autowired
    public PriceService priceService;

    @GetMapping(value="/products/{id}/price")
    ResponseEntity<Price> getById(@PathVariable("id") @Min(1) int id) {

        Price price = priceService.findById(id)
                .orElseThrow(()->new ProductNotFoundException("No Product with ID : "+id));

        return ResponseEntity.ok().body(price);
    }

    @PutMapping(value="/products/{id}/price")
    ResponseEntity<Price> putById(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Price priceReq) {

        Price price = priceService.save(priceReq);
        return ResponseEntity.ok().body(price);
    }

}
