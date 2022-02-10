package com.springweek3.springweek3.service;

import com.springweek3.springweek3.dto.FoodOrderDto;
import com.springweek3.springweek3.dto.FoodOrderRequestDto;
import com.springweek3.springweek3.dto.OrderDto;
import com.springweek3.springweek3.dto.OrderRequestDto;
import com.springweek3.springweek3.model.Food;
import com.springweek3.springweek3.model.FoodOrder;
import com.springweek3.springweek3.model.OrderSheet;
import com.springweek3.springweek3.model.Restaurant;
import com.springweek3.springweek3.repository.FoodOrderRepository;
import com.springweek3.springweek3.repository.FoodRepository;
import com.springweek3.springweek3.repository.OrderSheetRepository;
import com.springweek3.springweek3.repository.RestaurantRepository;
import com.springweek3.springweek3.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final FoodOrderRepository foodOrderRepository;
    private final OrderSheetRepository orderSheetRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(
            FoodOrderRepository foodOrderRepository,
            OrderSheetRepository orderSheetRepository,
            FoodRepository foodRepository,
            RestaurantRepository restaurantRepository) {
        this.foodOrderRepository = foodOrderRepository;
        this.orderSheetRepository = orderSheetRepository;
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }


    // 주문하기
    @Transactional
    public OrderDto addOrder(OrderRequestDto orderRequestDto) {

        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("찾으시는 식당이 존재하지 않습니다.")
        );

        List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        List<FoodOrder> foodOrders = new ArrayList<>();

        int sumPrice = 0;
        for(FoodOrderRequestDto foodOrderRequestDto: orderRequestDto.getFoods()) {

            // 주문 수량 유효성 검사
            OrderValidator.orderInputQuantityValidator(foodOrderRequestDto);

            Food food = foodRepository.findById(foodOrderRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("찾으시는 음식이 존재하지 않습니다.")
            );

            FoodOrder foodOrder = foodOrderRequestDto.toEntity(food);

            FoodOrderDto foodOrderDto = foodOrder.toResponseDto();

            foodOrderDtoList.add(foodOrderDto);
            foodOrderList.add(foodOrder);
            sumPrice += foodOrderDto.getPrice();
        }

        // 주문 가격 유효성 검사
        OrderValidator.orderInputPriceValidator(sumPrice, restaurant);

        OrderSheet orderSheet = orderRequestDto.toEntity(restaurant, sumPrice + restaurant.getDeliveryFee());

        orderSheetRepository.save(orderSheet);

        for (FoodOrder foodOrder : foodOrderList) {
            foodOrder = foodOrder.toFoodOrder(orderSheet);
            foodOrders.add(foodOrder);
        }

        foodOrderRepository.saveAll(foodOrders);

        OrderDto orderDto = orderSheet.toResponseDto(foodOrderDtoList);

        return orderDto;

    }

    // 메뉴 조회
    public List<OrderDto> getOrders() {
        List<OrderSheet> orderSheetList = orderSheetRepository.findAll();
        List<FoodOrder> foodOrderList = foodOrderRepository.findAll();
        List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (FoodOrder foodOrder : foodOrderList) {
            FoodOrderDto foodOrderDto = foodOrder.toResponseDto();
            foodOrderDtoList.add(foodOrderDto);
        }


        for (OrderSheet orderSheet : orderSheetList) {
            OrderDto orderDto = orderSheet.toResponseDto(foodOrderDtoList);
            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }
}
