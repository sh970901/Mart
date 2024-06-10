package com.hun.market.order.ship.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Builder
@Getter
public class ShippingAddress {

    private String street;

    public ShippingAddress() {

    }
    public ShippingAddress(String street) {
        this.street = street;
    }
//    private String city;
//    private String state;
//    private String postalCode;
//    private String country;

}
