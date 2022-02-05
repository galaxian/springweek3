package com.springweek3.springweek3.service;

import com.springweek3.springweek3.dto.FoodDto;
import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.FoodRepository;
import com.springweek3.springweek3.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public void addFoods(Long restaurantId, List<FoodDto> foodDtoList) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("")
        );
        for (FoodDto foodDto : foodDtoList) {
            String name = foodDto.getName();
            int price = foodDto.getPrice();
            if (price < 100 || price > 1000000 || price % 100 != 0) {
                throw new RuntimeException();
            }
            Optional<Food> checkFood = foodRepository.findByRestaurantAndName(restaurant, name);
            if (checkFood.isPresent()) {
                throw new RuntimeException();
            }

            Food food = Food.builder()
                    .restaurant(restaurant)
                    .name(name)
                    .price(price)
                    .build();

            foodRepository.save(food);
        }
    }

    public List<Food> getFoods(Long restaurantId) {
        List<Food> foodList = foodRepository.findByRestaurantId(restaurantId);
        List<Food> responseFoodList = new ArrayList<>();
        for (Food food : foodList) {
            responseFoodList.add(Food.builder()
                    .id(food.getId())
                    .name(food.getName())
                    .price(food.getPrice())
                    .build());
        }

        return responseFoodList;
    }
}
