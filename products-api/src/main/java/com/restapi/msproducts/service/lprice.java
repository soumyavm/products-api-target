package com.restapi.msproducts.service;

import com.restapi.msproducts.model.Price;

import java.util.Optional;

public interface lprice {
    Optional<Price> findById(int id);
    Price save(Price price);
}
