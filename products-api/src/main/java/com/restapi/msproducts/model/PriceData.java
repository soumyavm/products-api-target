package com.restapi.msproducts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor


public class PriceData implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("value")
    private BigDecimal price;

    private String currencyCode;


}