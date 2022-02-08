package com.springweek3.springweek3.controller;

import com.springweek3.springweek3.dto.FoodDto;
import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addFoods(
            @PathVariable Long restaurantId,
            @RequestBody List<FoodDto> foodDtoList) {

        foodService.addFoods(restaurantId, foodDtoList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId) {
        return foodService.getFoods(restaurantId);
    }
}
