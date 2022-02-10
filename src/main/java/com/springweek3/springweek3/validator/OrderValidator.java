package com.springweek3.springweek3.validator;

import com.springweek3.springweek3.dto.FoodOrderRequestDto;
import com.springweek3.springweek3.model.Restaurant;

public class OrderValidator {
    public static void orderInputQuantityValidator(FoodOrderRequestDto foodOrderRequestDto) {

        // 음식 주문 수량이 1개 미만이거나 100개 초과시 예외 발생
        if (foodOrderRequestDto.getQuantity() < 1 || foodOrderRequestDto.getQuantity() > 100) {
            throw new IllegalArgumentException("주문 수량은 1개 이상 100개 이하입니다.");
        }
    }

    public static void orderInputPriceValidator(int sumPrice, Restaurant restaurant) {

        // 주문 가격이 식당 최소 주문 가격보다 낮을 시 예외 발생
        if (sumPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("음식 값이 식당의 최소 주문비용보다 낮아 주문할 수 없습니다.");
        }
    }
}
