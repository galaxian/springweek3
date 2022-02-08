package com.springweek3.springweek3.validator;

import com.springweek3.springweek3.dto.RestaurantDto;

public class RestaurantValidator {
    public static void restaurantInputValidator(RestaurantDto restaurantDto) {

        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        if ((minOrderPrice > 100000 || minOrderPrice < 1000) ) {
            throw new RuntimeException("음식 최소 주문값은 1000원부터 100000원 까지 가능합니다.");
        }

        if (minOrderPrice%100 != 0) {
            throw new IllegalArgumentException("최소가격은 100원 단위로 입력가능합니다.");
        }

        if (deliveryFee > 10000 || deliveryFee < 0) {
            throw new IllegalArgumentException("배달비는 0원 부터 10000원까지 입력가능합니다.");
        }

        if (deliveryFee%500 != 0) {
            throw new RuntimeException("배달비는 500원 단위로 입력가능합니다.");
        }
    }
}
