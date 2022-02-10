package com.springweek3.springweek3.service;

import com.springweek3.springweek3.dto.RestaurantDto;
import com.springweek3.springweek3.dto.RestaurantResponseDto;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.RestaurantRepository;
import com.springweek3.springweek3.validator.DeliveryValidator;
import com.springweek3.springweek3.validator.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    // 식당 등록
    @Transactional
    public RestaurantResponseDto resisterRestaurant(RestaurantDto restaurantDto) {
        //식당 유효성 검사
        RestaurantValidator.restaurantInputValidator(restaurantDto);
        return restaurantRepository.save(restaurantDto.toEntity()).toRestaurantResponseDto(0);
    }

    // 식당 조회
    public List<RestaurantResponseDto> getRestaurant(int x, int y) {
        List<RestaurantResponseDto> restaurantResponseDtoList = new ArrayList<>();
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        for (Restaurant restaurant : restaurantList) {
            // 좌표를 통한 배달 가능지역 여부 판단
            if (DeliveryValidator.deliveryInputValidator(restaurant, x, y) == 1) {
                RestaurantResponseDto restaurantResponseDto = restaurant.toRestaurantResponseDto(500 * Math.abs((restaurant.getX() + restaurant.getY()) - (x + y)));
                restaurantResponseDtoList.add(restaurantResponseDto);
            }
        }

        // 좌표를 통한 유효성 검사
        DeliveryValidator.userLocationValidator(x, y);
        return restaurantResponseDtoList;
    }
}
