package com.springweek3.springweek3.model;

import com.springweek3.springweek3.dto.FoodOrderDto;
import com.springweek3.springweek3.dto.OrderDto;
import com.springweek3.springweek3.dto.OrderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class OrderSheet {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private int totalPrice;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    public OrderDto toResponseDto(List<FoodOrderDto> foodOrderDtoList) {
        return OrderDto.builder()
                .deliveryFee(restaurant.getDeliveryFee())
                .totalPrice(this.totalPrice)
                .foods(foodOrderDtoList)
                .restaurantName(restaurant.getName())
                .build();
    }

}
