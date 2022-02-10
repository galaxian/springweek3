package com.springweek3.springweek3.validator;

import com.springweek3.springweek3.dto.RestaurantDto;

public class RestaurantValidator {
    public static void restaurantInputValidator(RestaurantDto restaurantDto) {

        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        // 최소 주문값이 1000원 미만 이거나 100000원 초과시 예외
        if ((minOrderPrice > 100000 || minOrderPrice < 1000) ) {
            throw new IllegalArgumentException("음식 최소 주문값은 1000원부터 100000원 까지 가능합니다.");
        }

        // 주문 가격이 100단위가 아닐 시 예외 발생
        if (minOrderPrice%100 != 0) {
            throw new IllegalArgumentException("최소가격은 100원 단위로 입력가능합니다.");
        }

        // 배달비가 음수거나 10000원 초과시 예외 발생
        if (deliveryFee > 10000 || deliveryFee < 0) {
            throw new IllegalArgumentException("배달비는 0원 부터 10000원까지 입력가능합니다.");
        }

        // 배달비가 500원 단위가 아닐 시 예외 발생
        if (deliveryFee%500 != 0) {
            throw new IllegalArgumentException("배달비는 500원 단위로 입력가능합니다.");
        }

        // 식당 좌표가 음수일 경우 예외 발생
        if (restaurantDto.getX() < 0 || restaurantDto.getY() < 0) {
            throw new IllegalArgumentException("존재하지 않는 지역입니다.");
        }

        // 식당 좌표가 100 이상일 시 예외 발생
        if (restaurantDto.getX() > 99 || restaurantDto.getY() > 99) {
            throw new IllegalArgumentException("존재하지 않는 지역입니다.");
        }
    }
}
