package com.springweek3.springweek3.service;

import com.springweek3.springweek3.dto.RestaurantDto;
import com.springweek3.springweek3.dto.RestaurantResponseDto;
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

    public RestaurantResponseDto resisterRestaurant(RestaurantDto restaurantDto) {
        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        if ((minOrderPrice > 100000 || minOrderPrice < 1000) || minOrderPrice%100 != 0) {
            throw new RuntimeException();
        }
        if ((deliveryFee > 10000 || deliveryFee < 0) || deliveryFee%500 != 0) {
            throw new RuntimeException();
        }
        return restaurantRepository.save(restaurantDto.toEntity()).toRestaurantResponseDto();
    }

    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }
}
