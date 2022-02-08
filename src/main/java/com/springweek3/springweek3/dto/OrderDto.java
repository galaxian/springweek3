package com.springweek3.springweek3.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private String restaurantName;
    private List<FoodOrderDto> foods = new ArrayList<>();
    private int deliveryFee;
    private int totalPrice;

}
