package com.springweek3.springweek3.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDto {

    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
    private int x;
    private int y;

}
