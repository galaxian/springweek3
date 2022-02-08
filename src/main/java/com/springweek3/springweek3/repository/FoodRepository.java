package com.springweek3.springweek3.repository;

import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository <Food, Long>{
    Optional<Food> findByRestaurantAndName(Restaurant restaurant, String name);
    List<Food> findAllByRestaurantId(Long restaurantId);


    Food findByRestaurant(Restaurant restaurant);
}
