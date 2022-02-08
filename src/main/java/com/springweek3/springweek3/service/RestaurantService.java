package com.springweek3.springweek3.service;

import com.springweek3.springweek3.dto.RestaurantDto;
import com.springweek3.springweek3.dto.RestaurantResponseDto;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.RestaurantRepository;
import com.springweek3.springweek3.validator.RestaurantValidator;
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
        RestaurantValidator.restaurantInputValidator(restaurantDto);
        return restaurantRepository.save(restaurantDto.toEntity()).toRestaurantResponseDto();
    }

    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }
}
