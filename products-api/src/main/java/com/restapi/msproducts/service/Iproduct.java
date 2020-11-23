package com.restapi.msproducts.service;

import java.util.List;
import java.util.Optional;

import com.restapi.msproducts.model.Product;

public interface Iproduct {
	

	public Optional<Product> findById(int id) throws Exception;
	public void save(Product prd);

}
