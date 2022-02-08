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


    @Transactional
    public OrderDto addOrder(OrderRequestDto orderRequestDto) {

        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("")
        );

        List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        List<FoodOrder> foodOrders = new ArrayList<>();

        int sumPrice = 0;
        for(FoodOrderRequestDto foodOrderRequestDto: orderRequestDto.getFoods()) {

            if (foodOrderRequestDto.getQuantity() < 1 || foodOrderRequestDto.getQuantity() > 100) {
                throw new IllegalArgumentException("");
            }

            Food food = foodRepository.findById(foodOrderRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("")
            );

            FoodOrder foodOrder = FoodOrder.builder()
                    .quantity(foodOrderRequestDto.getQuantity())
                    .food(food)
                    .price(food.getPrice() * foodOrderRequestDto.getQuantity())
                    .build();

            FoodOrderDto foodOrderDto = FoodOrderDto.builder()
                    .name(foodOrder.getFood().getName())
                    .quantity(foodOrder.getQuantity())
                    .price(foodOrder.getPrice())
                    .build();

            foodOrderDtoList.add(foodOrderDto);
            foodOrderList.add(foodOrder);
            sumPrice += foodOrderDto.getPrice();
        }

        if (sumPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("");
        }

        OrderSheet orderSheet = OrderSheet.builder()
                .restaurant(restaurant)
                .totalPrice(sumPrice + restaurant.getDeliveryFee())
                .build();

        orderSheetRepository.save(orderSheet);

        for (FoodOrder foodOrder : foodOrderList) {
            foodOrder = FoodOrder.builder()
                    .id(foodOrder.getId())
                    .food(foodOrder.getFood())
                    .quantity(foodOrder.getQuantity())
                    .price(foodOrder.getPrice())
                    .orderSheet(orderSheet)
                    .build();
            foodOrders.add(foodOrder);
        }

        foodOrderRepository.saveAll(foodOrders);

        OrderDto orderDto = OrderDto.builder()
                .restaurantName(orderSheet.getRestaurant().getName())
                .foods(foodOrderDtoList)
                .deliveryFee(orderSheet.getRestaurant().getDeliveryFee())
                .totalPrice(orderSheet.getTotalPrice())
                .build();

        return orderDto;

    }

    public List<OrderDto> getOrders() {
        List<OrderSheet> orderSheetList = orderSheetRepository.findAll();
        List<FoodOrder> foodOrderList = foodOrderRepository.findAll();
        List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (FoodOrder foodOrder : foodOrderList) {
            FoodOrderDto foodOrderDto = FoodOrderDto.builder()
                    .price(foodOrder.getPrice())
                    .name(foodOrder.getFood().getName())
                    .quantity(foodOrder.getQuantity())
                    .build();
            foodOrderDtoList.add(foodOrderDto);
        }


        for (OrderSheet orderSheet : orderSheetList) {
            OrderDto orderDto = OrderDto.builder()
                    .restaurantName(orderSheet.getRestaurant().getName())
                    .foods(foodOrderDtoList)
                    .deliveryFee(orderSheet.getRestaurant().getDeliveryFee())
                    .totalPrice(orderSheet.getTotalPrice())
                    .build();
            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }
}
