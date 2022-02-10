package com.springweek3.springweek3.service;

import com.springweek3.springweek3.dto.FoodDto;
import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.FoodRepository;
import com.springweek3.springweek3.repository.RestaurantRepository;
import com.springweek3.springweek3.validator.FoodValidator;
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

    // 음식 등록
    @Transactional
    public void addFoods(Long restaurantId, List<FoodDto> foodDtoList) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("찾으시는 식당이 존재하지 않습니다.")
        );
        for (FoodDto foodDto : foodDtoList) {

            // 음식 유효성 검사
            FoodValidator.foodInputValidator(foodDto, restaurant);

            // 이미 등록된 음식인지 확인
            Optional<Food> checkFood = foodRepository.findByRestaurantAndName(restaurant, foodDto.getName());
            if (checkFood.isPresent()) {
                throw new IllegalArgumentException("이미 등록되어있는 음식입니다.");
            }

            foodRepository.save(foodDto.toEntity(restaurant)).toResponseFoodDto();
        }
    }

    // 음식 조회
    public List<Food> getFoods(Long restaurantId) {
        List<Food> foodList = foodRepository.findAllByRestaurantId(restaurantId);
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
