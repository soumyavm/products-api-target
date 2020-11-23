package com.restapi.msproducts.controller;

import com.restapi.msproducts.model.PriceData;
import com.restapi.msproducts.model.Product;
import com.restapi.msproducts.service.PriceService;
import com.restapi.msproducts.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    PriceService priceService;

    @Test
    @DisplayName("GET /products/id")
    void testGetProductById() throws Exception {

        Product prod = new Product();
        PriceData price = new PriceData();
        price.setPrice(BigDecimal.valueOf(88.9));
        price.setCurrencyCode("USD");
        prod.setId(5678121);
        prod.setName("Dummy Product");
        prod.setPrice(price);

        doReturn(prod).when(productService.findById(5678121));
        mockMvc.perform(get("/rest/v1/products/5678121"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdatePriceById() throws Exception {

        Product prod = new Product();
        PriceData price = new PriceData();
        price.setPrice(BigDecimal.valueOf(88.9));
        price.setCurrencyCode("USD");
        prod.setId(5678121);
        prod.setName("Dummy Product");
        prod.setPrice(price);

        doReturn(prod).when(productService).save(any());
        mockMvc.perform(put("/rest/v1/products/5678121")
                .contentType(MediaType.APPLICATION_JSON)
                .content((prod).toString()))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}