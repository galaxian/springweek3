package com.springweek3.springweek3.dto;

import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.FoodOrder;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodOrderRequestDto {

    private Long id;
    private int quantity;

    public FoodOrder toEntity(Food food) {
        return FoodOrder.builder()
                .food(food)
                .price(food.getPrice() * this.quantity)
                .quantity(this.quantity)
                .build();
    }
}
