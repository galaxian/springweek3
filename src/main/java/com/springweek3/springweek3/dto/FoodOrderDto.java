package com.springweek3.springweek3.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodOrderDto {

    private String name;
    private int quantity;
    private int price;

}
