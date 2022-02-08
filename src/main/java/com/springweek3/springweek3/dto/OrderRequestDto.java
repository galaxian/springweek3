package com.springweek3.springweek3.dto;

import com.springweek3.springweek3.model.OrderSheet;
import com.springweek3.springweek3.model.Restaurant;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDto {

    private Long restaurantId;
    private List<FoodOrderRequestDto> foods = new ArrayList<>();

    public OrderSheet toEntity(Restaurant restaurant, int totalPrice) {
        return OrderSheet.builder()
                .totalPrice(totalPrice)
                .restaurant(restaurant)
                .build();
    }

}
