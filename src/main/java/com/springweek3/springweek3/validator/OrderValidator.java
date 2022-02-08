package com.springweek3.springweek3.validator;

import com.springweek3.springweek3.dto.FoodOrderRequestDto;
import com.springweek3.springweek3.model.Restaurant;

public class OrderValidator {
    public static void orderInputQuantityValidator(FoodOrderRequestDto foodOrderRequestDto) {

        if (foodOrderRequestDto.getQuantity() < 1 || foodOrderRequestDto.getQuantity() > 100) {
            throw new IllegalArgumentException("주문 수량은 1개 이상 100개 이하입니다.");
        }
    }

    public static void orderInputPriceValidator(int sumPrice, Restaurant restaurant) {

        if (sumPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("");
        }
    }
}
