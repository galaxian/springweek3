package com.springweek3.springweek3.controller;

import com.springweek3.springweek3.dto.RestaurantDto;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public Restaurant resisterRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.resisterRestaurant(restaurantDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant() {
        return restaurantService.getRestaurant();
    }

}
