package com.springweek3.springweek3.repository;

import com.springweek3.springweek3.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
}
