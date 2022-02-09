package com.springweek3.springweek3.validator;

import com.springweek3.springweek3.dto.RestaurantDto;
import com.springweek3.springweek3.model.Restaurant;

public class DeliveryValidator {
    public static int deliveryInputValidator(Restaurant restaurant, int  x, int y) {

        if(Math.abs((restaurant.getX() + restaurant.getY()) - (x + y) ) > 3) {
            return 0;
        }

        return 1;
    }

    public static void userLocationValidator(int x, int y) {

        if (x < 0 || y < 0) {
            throw new NullPointerException("존재하지 않는 지역입니다.");
        }

        if (x > 99 || y > 99) {
            throw new NullPointerException("존재하지 않는 지역입니다.");
        }

    }
}
