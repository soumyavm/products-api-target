package com.restapi.msproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.msproducts.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

}

