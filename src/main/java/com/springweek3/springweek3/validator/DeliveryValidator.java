package com.springweek3.springweek3.validator;

import com.springweek3.springweek3.model.Restaurant;

public class DeliveryValidator {
    public static void deliveryInputValidator(Restaurant restaurant, int x, int y) {

        if(Math.abs((restaurant.getX() + restaurant.getY()) - (x+y) ) > 3) {
            throw new IllegalArgumentException("거리가 멀어 배달할 수 없는 지역입니다.");
        }

        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("존재하지 않는 지역입니다.");
        }

        if (x > 99 || y > 99) {
            throw new IllegalArgumentException("존재하지 않는 지역입니다.");
        }

    }
}
