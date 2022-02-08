package com.springweek3.springweek3.dto;

import com.springweek3.springweek3.model.Restaurant;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .deliveryFee(deliveryFee)
                .minOrderPrice(minOrderPrice)
                .build();
    }

}
