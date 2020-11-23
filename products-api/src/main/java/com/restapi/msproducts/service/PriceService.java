package com.restapi.msproducts.service;

import com.restapi.msproducts.model.Price;

import com.restapi.msproducts.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceService implements lprice{

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Optional<Price> findById(int id) {
        return priceRepository.findById(id);
    }
    @Override
    public Price save(Price price) {
        // TODO Auto-generated method stub
        return priceRepository.save(price);
    }



}
