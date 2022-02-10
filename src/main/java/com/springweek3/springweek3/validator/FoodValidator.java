package com.springweek3.springweek3.validator;

import com.springweek3.springweek3.dto.FoodDto;
import com.springweek3.springweek3.model.Restaurant;

public class FoodValidator {

    public static void foodInputValidator(FoodDto foodDto, Restaurant restaurant) {

        int price = foodDto.getPrice();

        // 음식 가격이 100원 미만이거나 1000000원 초과시 예외 처리
        if (price < 100 || price > 1000000) {
            throw new IllegalArgumentException("음식가격은 100원 이상 백만원 이하로 등록가능합니다.");
        }

        // 음식 단위가 100원 단위가 아닐 시 예외처리
        if (price % 100 != 0) {
            throw new IllegalArgumentException("음식가격은 100원 단위로 등록가능합니다.");
        }

    }
}
