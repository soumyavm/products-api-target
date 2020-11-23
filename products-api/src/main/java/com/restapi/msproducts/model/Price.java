package com.restapi.msproducts.model;


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
@Entity
@Table(name = "pricelist")
@EntityListeners(AuditingEntityListener.class)

public class Price implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        private int id;

        private BigDecimal price;

        private String currencyCode;


}
