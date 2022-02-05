package com.springweek3.springweek3.service;

import com.springweek3.springweek3.dto.RestaurantDto;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant resisterRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.getName())
                .minOrderPrice(restaurantDto.getMinOrderPrice())
                .deliveryFee(restaurantDto.getDeliveryFee())
                .build();
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }
}
