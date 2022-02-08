package com.springweek3.springweek3.validator;

import com.springweek3.springweek3.dto.FoodDto;
import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FoodValidator {

    public static void foodInputValidator(FoodDto foodDto, Restaurant restaurant) {

        String name = foodDto.getName();
        int price = foodDto.getPrice();

        if (price < 100 || price > 1000000) {
            throw new IllegalArgumentException();
        }

        if (price % 100 != 0) {
            throw new IllegalArgumentException();
        }

    }
}
