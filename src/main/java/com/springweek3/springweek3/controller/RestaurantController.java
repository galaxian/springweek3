package com.springweek3.springweek3.controller;

import com.springweek3.springweek3.dto.RestaurantDto;
import com.springweek3.springweek3.dto.RestaurantResponseDto;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.service.RestaurantService;
import com.springweek3.springweek3.validator.DeliveryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public ResponseEntity<RestaurantResponseDto> resisterRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return ResponseEntity.ok()
                .body(restaurantService.resisterRestaurant(restaurantDto));
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantResponseDto>> getRestaurant(@RequestParam(value = "x", defaultValue = "0") int x, @RequestParam(value = "y", defaultValue = "0") int y) {
        return ResponseEntity.ok().body(restaurantService.getRestaurant(x, y));
    }

}
