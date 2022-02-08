package com.springweek3.springweek3.service;

import com.springweek3.springweek3.dto.RestaurantDto;
import com.springweek3.springweek3.dto.RestaurantResponseDto;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.RestaurantRepository;
import com.springweek3.springweek3.validator.DeliveryValidator;
import com.springweek3.springweek3.validator.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantResponseDto resisterRestaurant(RestaurantDto restaurantDto) {
        RestaurantValidator.restaurantInputValidator(restaurantDto);
        return restaurantRepository.save(restaurantDto.toEntity()).toRestaurantResponseDto(0);
    }

    public List<RestaurantResponseDto> getRestaurant(int x, int y) {
        List<RestaurantResponseDto> restaurantResponseDtoList = new ArrayList<>();
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        for (Restaurant restaurant : restaurantList) {
            RestaurantResponseDto restaurantResponseDto = restaurant.toRestaurantResponseDto(Math.abs((restaurant.getX() + restaurant.getY()) - (x+y) ));

            DeliveryValidator.deliveryInputValidator(restaurant, x, y);

            restaurantResponseDtoList.add(restaurantResponseDto);
        }
        return restaurantResponseDtoList;
    }
}
