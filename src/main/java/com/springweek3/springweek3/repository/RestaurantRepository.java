package com.springweek3.springweek3.repository;

import com.springweek3.springweek3.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
