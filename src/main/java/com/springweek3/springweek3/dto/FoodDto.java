package com.springweek3.springweek3.dto;

import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.Restaurant;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

    private Long id;
    private String name;
    private int price;

    public Food toEntity(Restaurant restaurant) {
        return Food.builder()
                .restaurant(restaurant)
                .name(name)
                .price(price)
                .build();
    }
}
