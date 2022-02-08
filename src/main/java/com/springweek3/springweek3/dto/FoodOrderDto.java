package com.springweek3.springweek3.dto;

import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.FoodOrder;
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
