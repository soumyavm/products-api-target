package com.restapi.msproducts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.msproducts.exception.ProductNotFoundException;
import com.restapi.msproducts.model.Price;
import com.restapi.msproducts.model.PriceData;
import com.restapi.msproducts.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService implements Iproduct {

	private static final String HOST = "http://127.0.0.1:8000/rest/v1/products/";
	@Autowired
	private PriceService priceService;

	@Override
	public Optional<Product> findById(int id) throws Exception {

		Product prd = new Product();

		PriceData priceData = null;


			prd.setName(getNameById(id));
			priceData = getPriceDataById(id);
			prd.setId(id);
			prd.setPrice(priceData);






		return Optional.ofNullable(prd);

	}

	private String getNameById(int id) throws JsonProcessingException, IOException {
		/* String responseBody = null;
		ResponseEntity<String> response = null;


		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(getURIPathForName(id)).build();

		RestTemplate restTemplate = new RestTemplate();
		response = restTemplate.getForEntity(uriComponents.encode().toUri(), String.class);

		responseBody = response.getBody();
		ObjectMapper mapper = new ObjectMapper();

		JsonNode productRootNode = mapper.readTree(responseBody);


		if (productRootNode != null) {
			// parse the product data and send the name
		} */

		//returning Dummy product name everytime
		return "Dummy product";
	}

	private String getURIPathForName(int id) {
		String url = "http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		return url ;
	}

	@Override
	public void save(Product prod) {

		final String url = getURIPathForPrice(prod.getId());
		RestTemplate restTemplate = new RestTemplate();
		Price price = new Price();
		price.setId(prod.getId());
		price.setPrice(prod.getPrice().getPrice());
		price.setCurrencyCode(prod.getPrice().getCurrencyCode());
		restTemplate.put ( url, price );
		return ;
	}

	public String getURIPathForPrice(int productId) {
		String url = HOST + productId + "/price";
		return url;
	}

	public PriceData getPriceDataById(int id) throws JsonProcessingException, IOException {

		String responseBody = null;
		ResponseEntity<String> response = null;


		RestTemplate restTemplate = new RestTemplate();
		response = restTemplate.getForEntity(getURIPathForPrice(id), String.class);
		responseBody = response.getBody();
		ObjectMapper mapper = new ObjectMapper();

		JsonNode productRootNode = mapper.readTree(responseBody);
		PriceData price = new PriceData();

		if (productRootNode != null) {
			price.setPrice(BigDecimal.valueOf(productRootNode.get("price").asDouble()));
			price.setCurrencyCode((productRootNode.get("currencyCode").toString()));
		}

		return price;
	}
}
