package com.hun.market.order.ship.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShippingAddress {

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'INNOPLE'")
    private String street;
//    private String city;
//    private String state;
//    private String postalCode;
//    private String country;

}
