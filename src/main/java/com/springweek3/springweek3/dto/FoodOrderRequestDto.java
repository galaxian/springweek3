package com.springweek3.springweek3.dto;

import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.FoodOrder;
import com.springweek3.springweek3.model.OrderSheet;
import com.springweek3.springweek3.model.Restaurant;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodOrderRequestDto {

    private Long id;
    private int quantity;

}
