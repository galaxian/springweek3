package com.springweek3.springweek3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESTAURANT_ID")
    private Long id;

    @Column(name = "RESTAURANT_NAME",nullable = false)
    private String name;

    @Column(name = "RESTAURANT_MIN_ORDER_PRICE",nullable = false)
    private int minOrderPrice;

    @Column(name = "RESTAURANT_DELIVERYFEE", nullable = false)
    private int deliveryFee;

}
