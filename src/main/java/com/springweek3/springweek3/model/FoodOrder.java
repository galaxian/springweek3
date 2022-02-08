package com.springweek3.springweek3.model;

import com.springweek3.springweek3.dto.FoodOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(nullable = false)
    private OrderSheet orderSheet;

    public FoodOrderDto toResponseDto() {
        return FoodOrderDto.builder()
                .quantity(this.quantity)
                .name(this.food.getName())
                .price(this.price)
                .build();
    }

    public FoodOrder toFoodOrder(OrderSheet orderSheet) {
        return FoodOrder.builder()
                .food(this.food)
                .quantity(this.quantity)
                .price(this.price)
                .orderSheet(orderSheet)
                .build();
    }

}
