package com.springweek3.springweek3.dto;

import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.FoodOrder;
import com.springweek3.springweek3.model.OrderSheet;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.RestaurantRepository;
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
