package com.restapi.msproducts.controller;

import com.restapi.msproducts.exception.ProductNotFoundException;
import com.restapi.msproducts.model.Product;
import com.restapi.msproducts.service.PriceService;
import com.restapi.msproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/rest/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private PriceService priceService;
	
	@GetMapping(value="/products/{id}")
	ResponseEntity<Product> getById(@PathVariable("id") @Min(1) int id)  {

		try {
			Product product = productService.findById(id)
					.orElseThrow(() -> new ProductNotFoundException("No Product with ID : " + id));
			return ResponseEntity.ok().body(product);
		} catch(Exception e) {
			throw new ProductNotFoundException("No Product with ID : " + id);
		}


	}
	

	@PutMapping(value="/products/{id}")
	ResponseEntity<String> updateProduct(@PathVariable("id")  @Min(1) int id, @Valid @RequestBody Product inprod) throws Exception{

		try {
			productService.findById(id)
					.orElseThrow(() -> new ProductNotFoundException("No Product with ID : " + id));
			productService.save(inprod);
			return new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception e) {
			throw new ProductNotFoundException("No Product with ID : " + id);
		}
	}
	


}
